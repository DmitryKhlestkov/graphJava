import java.util.ArrayList;
import java.util.Collections;

public class Polygon3d extends Shape {

    private ArrayList<Vector3d> _pointsList;
    Boolean canBuildPolygon;

    /**
     *  онструктор многоугольника по умолчанию
     */
    Polygon3d() {
        _pointsList = new ArrayList<Vector3d>();
    }

    /**
     *  онструктор многоугольника по массиву точек
     * @param pointsList
     */
    Polygon3d(ArrayList<Vector3d> pointsList) {
        this._pointsList = pointsList;
    }

    /**
     * ѕринадлежность точки плоскости
     * ѕлоскость строитс€ по трем первым точкам заданного массива точек
     * —читаем, что всегда начальные три точки образуют плоскость
     * @param point
     */
    boolean PointBelongLine( Vector3d point )
    {
        double x = point.get_x(), y = point.get_y(), z = point.get_z();
        Vector3d point0 = new Vector3d( this._pointsList.get(0) );
        Vector3d point1 = new Vector3d( this._pointsList.get(1) );
        Vector3d point2 = new Vector3d( this._pointsList.get(2) );

        double a = point1.get_x() - point0.get_x(); // x_1 - x_0
        double b = point2.get_x() - point0.get_x(); // x_2 - x_0
        double c = point1.get_y() - point0.get_y(); // y_1 - y_0
        double d = point2.get_y() - point0.get_y(); // y_2 - y_0
        double e = point1.get_z() - point0.get_z(); // z_1 - z_0
        double f = point2.get_z() - point0.get_z(); // z_2 - z_0

        // подставим в уравнение плоскости точку, если принадлежит то det == 0
        // точки не лежат на одной пр€мой
        double det = (x-point0.get_x())*( c*f - e*d )-(y-point0.get_y())*(a*f-e*b)-(z-point0.get_z())*(a*d-c*b);
        if(det == 0)
            return true;
        return false;
    }

    /**
     * ѕринадлежат ли точки одной плосости
     * ћножество точе уже должно быть инициализированно
     * подставл€ем каждую точку в уравнение плоскости
     * @return
     */
    boolean PointBelongSamePlane()
    {
        if( this._pointsList.size() < 3 )
            return false;

        for ( int i = 3; i < this._pointsList.size(); ++i )
            if( !PointBelongLine( this._pointsList.get(i) ) )
                return false;
        return true;
    }

    /**
     * ќпределение с какой стороны от вектора ab находитс€ точка c
     * @param a
     * @param b
     * @param c
     * @return положительное значение - слева
     *         отрицательно значение - справа
     *         нуль - на одной пр€мой
     */
    double RotatePoint(Vector3d a, Vector3d b, Vector3d c)
    {
        // d = ab, e = bc
        // z = d_x*e_y - d_y*e_x
        return ((b.get_x() - a.get_x())*(c.get_y() - b.get_y())) - ((b.get_y() - a.get_y())*(c.get_x() - b.get_x()));
    }

    /**
     * —ортировка точек против часовой стрелки
     * алг. грэхема
     */
    ArrayList<Vector3d> SortPoint()
    {
        //найдем точку с миннимальной X координатой
        int index_min_x = 0;
        for (int i = 1; i < this._pointsList.size(); ++i) {
            if( this._pointsList.get(index_min_x).get_x() > this._pointsList.get(i).get_x() )
                index_min_x = i;
        }

        ArrayList<Vector3d> pointList = (ArrayList<Vector3d>) this._pointsList.clone();
        // переестим в начало элемент с min координатой X
        Collections.swap(pointList, 0, index_min_x);

        // сама сортировка
        for ( int i = 2, j = 0; i < pointList.size(); ++i ) {
            j = i;
            while(j>1 && RotatePoint(pointList.get(0), pointList.get(j-1), pointList.get(j))<0 ) {
                Collections.swap(pointList, j, j-1);
                --j;
            }
        }
        return pointList;
    }

    /**
     * ѕроверка, что точки образуют выпуклый многоугольник
     * ¬се следующие точки должны лежать слева от расматриваемого вектора
     * @param pointList точки лежат в одной плоскости и отсортированны против часовой стрелки
     * @return
     */
    boolean IsConvexPolygon(ArrayList<Vector3d> pointList)
    {
        for(int i = 0; i < pointList.size()-2; ++i)
        {
            if( RotatePoint(pointList.get(i), pointList.get(i+1), pointList.get(i+2)) < 0)
                return  false;
        }
        return true;
    }

    /**
     * ѕроверка: можно ли построить многоугольник
     * Ќа вход подаетс€ упор€доченный массив точек, в пор€дке обхода по часовой стрелке или против
     * @return
     */
    @Override
    protected boolean ExistShape() {

        if( canBuildPolygon != null && canBuildPolygon.booleanValue() )
            return canBuildPolygon.booleanValue();

        // проверим, что точки лежат в одной плоскости
        if(!PointBelongSamePlane())
            return  false;

        // отсортируем точки
        ArrayList<Vector3d> pointList = SortPoint();

        // проверка, можно ли построить выпуклый многоугольник
        return IsConvexPolygon(pointList);
    }

    /**
     * ѕлощадь многоугольника
     * —уммма площадей треугольника через векторное произведение = площадь многоугольника
     * @return
     */
    @Override
    protected double AreaShape() {
        double area = 0;
        if(ExistShape()) {
            for (int arrIndex = 0; arrIndex < _pointsList.size() - 2; ++arrIndex) {
                // 1-а€ точка отрезка
                Vector3d a = new Vector3d(new Vector3d(_pointsList.get(arrIndex)),new Vector3d(_pointsList.get(arrIndex+1)));
                // 2-а€ точка отрезка
                Vector3d b = new Vector3d(new Vector3d(_pointsList.get(arrIndex)),new Vector3d(_pointsList.get(arrIndex+2)));
                area += Vector3d.VectorProduct(a, b).vector3DNorm();
            }
        }

        return 0.5*area;
    }

    /**
     * ѕериметр многоугольника, сложение длин всех ребер
     * @return
     */
    @Override
    protected double PerimeterShape() {
        double perimetr = 0;
        if(ExistShape()) {
            ArrayList<Vector3d> poinList = (ArrayList<Vector3d>) this._pointsList.clone();
            poinList.add(poinList.get(0));
            for (int arrIndex = 0; arrIndex < poinList.size() - 1; ++arrIndex) {
                LineSegment3d direct = new LineSegment3d(poinList.get(arrIndex), poinList.get(arrIndex + 1));
                perimetr += direct.DirectLength();
            }
        }
        return perimetr;
    }

    public static void main(String[] args) {

        try
        {
        Polygon3d polygon3d = new Polygon3d();
        Vector3d p1 = new Vector3d(1,1,0);
        Vector3d p2 = new Vector3d(4,0,0);
        Vector3d p3 = new Vector3d(6,3,0);
        Vector3d p4 = new Vector3d(4,5,0);
        Vector3d p5 = new Vector3d(1,4,0);
        ArrayList<Vector3d> pointList = new ArrayList<Vector3d>();
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);
        pointList.add(p5);

        Polygon3d polygon3d1 = new Polygon3d(pointList);

        System.out.println( "Exist figure = " + polygon3d1.ExistShape() );
        if( polygon3d1.ExistShape() ) {
            System.out.println( "Perimetr = " + polygon3d1.PerimeterShape() );
            System.out.println( "Area = " + polygon3d1.AreaShape() );
        }

        }
        catch (Exception e)
        {
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getMessage());
        }
    }
}