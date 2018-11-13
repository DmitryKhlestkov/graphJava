/**
 * ����� �������
 */
public class LineSegment3d {

    private static final double DEFAULT_EPS = 0.00001;

    public Vector3d _a, _b;//����� ������ - �����

    /**
     * ����������� �� ���������
     * �������������� �� ��������� ��������� �������� �� Ox
     */
    public LineSegment3d(){
        this._a = new Vector3d();
        this._b = new Vector3d(1,0,0);
    }

    /**
     * ����������� ������� ��  ���� ������
     * @param a - 1-� �����
     * @param b - 2-� �����
     */
    public LineSegment3d(Vector3d a, Vector3d b){
        this._a = new Vector3d( a );
        this._b = new Vector3d( b );
    }

    /**
     * ����� �������
     * @return - sqrt( (x2-x1)^2 + (y2-y1)^2 + (z2-z1)^2 )
     */
    public double DirectLength(){
        return Vector3d.sum(this._b, this._a.Negate()).vector3DNorm();
    }

    /* TODO:
    * ��� ��������, ��� ����������� ����� ���� ����� ����
    * */
    /**
     * ����������� �� ����� ������
     * ����������� ����� � ��������� ������ ( x - x1) / ( x2 - x1 ) = ( x - x1) / ( x2 - x1 ) = ( x - x1) / ( x2 - x1 )
     * @param point - �����
     * @return - true - �����������, false - �� �����������
     */
    public  boolean PointBelongsDirect(Vector3d point) {
        try {
            if ((((point.get_x() - _a.get_x()) / (_b.get_x() - _a.get_x())) -
                    ((point.get_y() - _a.get_y()) / (_b.get_y() - _a.get_y())) -
                    ((point.get_z() - _a.get_z()) / (_b.get_z() - _a.get_z()))) < DEFAULT_EPS)
                return true;
        }
        catch ( Exception e ){
            System.out.println( "����� �� �������� � ��������� �����������" + e.getMessage() );
        }

        return false;
    }

    @Override
    public String toString() {
        return "Direct3D{" +
                "_a=" + _a +
                ", _b=" + _b +
                '}';
    }

    /**
     * �������� �������
     * @return - �����
     */
    public Vector3d MidPoint(){
        return new Vector3d(Vector3d.DivVector(Vector3d.sum(this._a, this._b),2));
    }

    public static void main(String[] args) {
        LineSegment3d d = new LineSegment3d( new Vector3d(1,2,3), new Vector3d(3,4,5));
        System.out.println( d + " " + d.DirectLength() + " " + d.MidPoint() );
    }

}
