import java.util.ArrayList;

/**
 * Класс точки( вектор )
 */
class Vector3d {

    private double _x, _y, _z;

    /**
     * Конструктор по умолчанию, заполняем координаты вектора(точки) нулями
     */
    public Vector3d() {
        this._x = 0;
        this._y = 0;
        this._z = 0;
    }

    /**
     * Констуктор вектора(точки) по 3-м координатам
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector3d(double x, double y, double z) {
        this._x = x;
        this._y = y;
        this._z = z;
    }

    /**
     * Кончтруктор по точке
     * @param point3D
     */
    public Vector3d(Point3D point3D) {
        this._x = point3D.get_x();
        this._y = point3D.get_y();
        this._z = point3D.get_z();
    }

    /**
     * Конструктор по существующему вектору(точке)
     *
     * @param vector3d
     */
    public Vector3d(Vector3d vector3d) {
        this._x = vector3d.get_x();
        this._y = vector3d.get_y();
        this._z = vector3d.get_z();
    }

    /**
     * Конструктор по двум точкам
     * @param vector3d_1
     * @param vector3d_2
     */
    public Vector3d(Vector3d vector3d_1, Vector3d vector3d_2) {
        this._x = vector3d_2.get_x()-vector3d_1.get_x();
        this._y = vector3d_2.get_y()-vector3d_1.get_y();
        this._z = vector3d_2.get_z()-vector3d_1.get_z();
    }

    /**
     * Скалярное произведение векторов
     *
     * @param vector3d_1
     * @param vector3d_2
     * @return
     */
    public Double scalar(Vector3d vector3d_1, Vector3d vector3d_2) {
        return (vector3d_1.get_x() * vector3d_2.get_x()) +
                (vector3d_1.get_y() * vector3d_2.get_y()) +
                (vector3d_1.get_z() * vector3d_2.get_z());
    }

    /**
     * Векторное произведение
     * @param vector3d1
     * @param vector3d2
     * @return
     */
    public static Vector3d VectorProduct(Vector3d vector3d1, Vector3d vector3d2){
        return new Vector3d(vector3d1.get_y()*vector3d2.get_z() - vector3d1.get_z()*vector3d2.get_y(),
                vector3d1.get_x()*vector3d2.get_z() - vector3d1.get_z()*vector3d2.get_x(),
                vector3d1.get_x()*vector3d2.get_y() - vector3d1.get_y()*vector3d2.get_x());
    }
    /**
     * Норма вектора( длина )
     */
    public double vector3DNorm() {
        return Math.sqrt(Math.pow(_x, 2) + Math.pow(_y, 2) + Math.pow(_z, 2));
    }


    /**
     * Норма двух векторов
     * @param point1
     * @param point2
     * @return
     */
    public static double norma(Vector3d point1, Vector3d point2){
        ArrayList<Double> arr = new ArrayList<Double>();
        double delX = point1.get_x()-point2.get_x();
        double delY = point1.get_y()-point2.get_y();
        double delZ = point1.get_z()-point2.get_z();

        arr.add(delX);
        arr.add(delY);
        arr.add(delZ);

        double sum=0;
        for(int i=0; i<arr.size(); ++i){
            sum +=(arr.get(i)*arr.get(i));
        }
        return Math.sqrt(sum);
    }


    /**
     * Нормирование вектора
     * На вход только проинииализированный вектор
     */
    public Vector3d vector3DNormalization() {
        Vector3d normalized_vector = new Vector3d();

        try {
            double vector3d_length = vector3DNorm();
            normalized_vector = new Vector3d(this._x / vector3d_length,
                    this._y / vector3d_length,
                    this._z / vector3d_length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return normalized_vector;
    }

    /**
     * Сложение двух векторов
     * @param firstVector
     * @param secondVector
     * @return вектор
     */
    public static Vector3d sum(Vector3d firstVector, Vector3d secondVector){
        return new Vector3d(firstVector.get_x()+secondVector.get_x(),
                firstVector.get_y()+secondVector.get_y(),
                firstVector.get_z()+secondVector.get_z());
    }

    /**
     * Взятие обратного вектора(точки) ( (-1)*vector )
     * @return
     */
    public Vector3d Negate(){
        return new Vector3d(-this._x, -this._y, -this._z);
    }


    /**
     * Деление вектора(точки) на число
     * @param vector
     * @param devider
     * @return
     */
    public static Vector3d DivVector( Vector3d vector, double devider ){
        return new Vector3d(vector.get_x()/devider, vector.get_y()/devider, vector.get_z()/devider);
    }

    ////методы доступа
    public double get_x() {
        return _x;
    }

    public void set_x(double _x) {
        this._x = _x;
    }

    public double get_y() {
        return _y;
    }

    public void set_y(double _y) {
        this._y = _y;
    }

    public double get_z() {
        return _z;
    }

    public void set_z(double _z) {
        this._z = _z;
    }
    ////

    @Override
    public String toString() {
        return "Vector3D{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }

    public static void main(String[] args) {

        Vector3d v = new Vector3d( 1,2,3 );
        double norm = v.vector3DNorm();
        System.out.println( "Vector = " );
        System.out.println( v );
        Point3D p = new Point3D( 1,3 , 5 );
        System.out.println( p.toString() );
    }
}
