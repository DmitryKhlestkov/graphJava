import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TriangleTest {

    private static final double DELTA = 0.1;
    ArrayList<Vector3d> vectorList = new ArrayList<Vector3d>();
    Triangle3D triangle;

    public void setUp() {
        vectorList.add(new Vector3d(0, 0, 0));
        vectorList.add(new Vector3d(3, 0, 0));
        vectorList.add(new Vector3d(0, 3, 0));
        try {
            triangle = new Triangle3D(vectorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testvector3DNorm() throws Exception {
        setUp();
        Vector3d equivalent = new Vector3d(0.8, 0.8, 0);
        Vector3d result = triangle.BisectorIntersectionPoint();
        double norm = Vector3d.norma(equivalent, result);
        Assert.assertEquals("The norm error", norm, DELTA, 5d);
    }
}
