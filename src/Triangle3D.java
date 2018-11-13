import java.util.ArrayList;

/**
 * Класс треугольник
 */

public class Triangle3D extends Polygon3d {

    ArrayList<Vector3d> _pointList;

    Triangle3D(){ super(); }
    /**
     *Конструктор по масиву точек
     */
    Triangle3D(ArrayList<Vector3d> pointList) throws Exception {
        super(pointList);
        if( pointList.size() != 3) {
                throw new Exception("Треугольник строится по 3-м точкам." +
                        "Задано " + pointList.size() + " точек!!!" +
                        "Задайте верное количество точек");
        }

        this._pointList = pointList;
    }

    /**
     * Точка пересечения медиан треугольника
     * @return
     */
    Vector3d MedianIntersectionPoint() throws Exception {
        if(!ExistShape())
            throw  new Exception("Координаты треугольника не проинициализированны");

        return new Vector3d( (_pointList.get(0).get_x() + _pointList.get(1).get_x() + _pointList.get(2).get_x())/3,
                (_pointList.get(0).get_y() + _pointList.get(1).get_y() + _pointList.get(2).get_y())/3,
                (_pointList.get(0).get_z() + _pointList.get(1).get_z() + _pointList.get(2).get_z())/3);
    }

    /**
     * Точка пересечения бессиктрис треугольника
     * @return
     */
    Vector3d BisectorIntersectionPoint() throws Exception {
        if(!ExistShape())
            throw  new Exception("Координаты треугольника не проинициализированны");

        double a = new LineSegment3d( _pointList.get(0), _pointList.get(1) ).DirectLength();
        double b = new LineSegment3d( _pointList.get(1), _pointList.get(2) ).DirectLength();
        double c = new LineSegment3d( _pointList.get(2), _pointList.get(0) ).DirectLength();

        return new Vector3d( (_pointList.get(0).get_x() * b + _pointList.get(1).get_x() * c + _pointList.get(2).get_x() * a )/PerimeterShape(),
                (_pointList.get(0).get_y() * b + _pointList.get(1).get_y() * c + _pointList.get(2).get_y() * a )/PerimeterShape(),
                (_pointList.get(0).get_z() * b + _pointList.get(1).get_z() * c + _pointList.get(2).get_z() * a )/PerimeterShape());
    }

    /**
     * Печатает отрезки от центра вписанной окружности до всех вершин
     * может кинуть исключение, что фигура не существует
     */
    void PrintLineSigment() throws Exception {
        Vector3d centrInscribedCircle = new Vector3d( BisectorIntersectionPoint() );

        for (int i = 0; i < _pointList.size(); ++i){
            System.out.println( new LineSegment3d(centrInscribedCircle, _pointList.get(i)).toString() );
        }
    }

    /**
     * Площадь треугольника
     * @return
     */
    @Override
    protected double AreaShape() {
        return super.AreaShape();
    }

    /**
     * Периметр треугльника
     * @return
     */
    @Override
    protected double PerimeterShape() {
        return super.PerimeterShape();
    }

    /**
     * Существует ли треугольник
     * по заданным точам
     * достаточно что три точки в массиве
     * @return
     */
    @Override
    protected boolean ExistShape() {
        return super.ExistShape();
    }

    public static void main(String[] args) {

        try
        {
            Vector3d p1 = new Vector3d(0,0,0);
            Vector3d p2 = new Vector3d(8,0,0);
            Vector3d p3 = new Vector3d(0,5,0);

            ArrayList<Vector3d> pointList = new ArrayList<Vector3d>();
            pointList.add(p1);
            pointList.add(p2);
            pointList.add(p3);

            Triangle3D triangle3d = new Triangle3D(pointList);

            System.out.println( "Exist figure = " + triangle3d.ExistShape() );
            if( triangle3d.ExistShape() ) {
                System.out.println( "Perimetr = " + triangle3d.PerimeterShape() );
                System.out.println( "Area = " + triangle3d.AreaShape() );
                System.out.println( "Centr inscribed circle = " + triangle3d.BisectorIntersectionPoint() );

                triangle3d.PrintLineSigment();
            }


        }
        catch (Exception e)
        {
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getMessage());
        }
    }
}
