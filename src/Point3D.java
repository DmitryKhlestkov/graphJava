
public class Point3D {
    private double _x, _y, _z;

    public Point3D() {
        this._x = 0;
        this._y = 0;
        this._z = 0;
    }

    public Point3D(double _x, double _y, double _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    public double get_x() {
        return _x;
    }

    public Double get_y() {
        return _y;
    }

    public double get_z() {
        return _z;
    }

    public void set_x(double _x) {
        this._x = _x;
    }

    public void set_y(double _y) {
        this._y = _y;
    }

    public void set_z(double _z) {
        this._z = _z;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }
}
