import org.junit.Assert;
import org.junit.Test;

/**
 * Created by MSI on 27.12.2017.
 */


public class TestVector {

    Vector3d vector;

    public void setUp()
    {
        vector = new Vector3d(0,0,6);
    }

    @Test
    public void testvector3DNorm()
    {
        setUp();
        double result = vector.vector3DNorm();
        Assert.assertEquals( "The norm error", result, (double)6, 5d);
    }
}
