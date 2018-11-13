import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by MSI on 27.12.2017.
 */
public class TestPolygon {

    Polygon3d polygon3d = new Polygon3d();


    public void setUp()
    {
        Vector3d p1 = new Vector3d(0,0,0);
        Vector3d p2 = new Vector3d(2,0,0);
        Vector3d p3 = new Vector3d(2,2,0);
        Vector3d p4 = new Vector3d(0,2,0);

        ArrayList<Vector3d> pointList = new ArrayList<Vector3d>();
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);
        pointList.add(p4);

        Polygon3d polygon3d1 = new Polygon3d(pointList);
    }


    @Test
    public void testPolygonArea()
    {

        double resultArea = polygon3d.AreaShape();
        Assert.assertEquals("The area error", resultArea, 4, 5d);

    }

    public void testPolygonPerimetr()
    {
        double resultPer = polygon3d.PerimeterShape();
        Assert.assertEquals("The perim error", resultPer, 8, 5d);
    }

    public void testPolygonExistPolygon()
    {
        boolean existPol = polygon3d.ExistShape();
        Assert.assertTrue("The exist polygon error", existPol);
    }

    public void testPolygonPointBelonSamePlane()
    {
        boolean existPol = polygon3d.PointBelongSamePlane();
        Assert.assertTrue("The ��� error", existPol );
    }
}
