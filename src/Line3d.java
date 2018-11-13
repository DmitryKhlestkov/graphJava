/**
 * ����� ������
 */
public class Line3d {

    // ����� � ������������ ������
    Vector3d _point, _directionVector;

    Line3d(){}

    /**
     * ����������� �� ����� � ������������� �������
     * @param point
     * @param directionVector
     */
    Line3d( Vector3d point, Vector3d directionVector){
        this._point = point;
        this._directionVector = directionVector;
    }

    /**
     * ������ ������ �� ���� ������
     * � �������� ������� ����� ������ ����� ����� ����� �����
     * @param point1
     * @param point2
     */
    void ConstructLine(Vector3d point1, Vector3d point2){
        this._directionVector = new Vector3d( point2.get_x()-point1.get_x(),
                point2.get_y()-point1.get_y(),
                point2.get_z()-point1.get_z() );
        this._point = point1;
    }

    // ����� ����������� ������
    // �� ���� 2-� ������
    // ������ �� ������������ ���� ���� ���� ����������� 0, ����� ���� ��������� �� 0 - �� ������ �� ������������.
    public static Vector3d IntersectionPoint(Line3d line1, Line3d line2){
        double x0 = line1._point.get_x(), y0 = line1._point.get_y(), z0 = line1._point.get_z();
        double p0 = line1._directionVector.get_x(),
                q0 = line1._directionVector.get_y(),
                r0 = line1._directionVector.get_z();

        double x1 = line2._point.get_x(), y1 = line2._point.get_x(), z1 = line2._point.get_x();
        double p1 = line2._directionVector.get_x(),
                q1 = line2._directionVector.get_y(),
                r1 = line2._directionVector.get_z();

        double x_intersection = (x0 * q0 * p1 - x1 * q1 * p0 - y0 * p0 * p1 + y1 * p0 * p1) / (q0 * p1 - q1 * p0);
        double y_intersection = (y0 * p0 * q1 - y1 * p1 * q0 - x0 * q0 * q1 + x1 * q0 * q1) / (p0 * q1 - p1 * q0);
        double z_intersection = (z0 * q0 * r1 - z1 * q1 * r0 - y0 * r0 * r1 + y1 * r0 * r1) / (q0 * r1 - q1 * r0);

        return new Vector3d(x_intersection, y_intersection, z_intersection);
    }

    //**
    /*����� � ��������
    5 ����
    �������� � �������
    /*/

}
