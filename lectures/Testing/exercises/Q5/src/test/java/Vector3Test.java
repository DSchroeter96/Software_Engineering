import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class Vector3Test {

    @Test
    public void sampleTest() {
        assertThat(1, is(1));
    }

    @Test
    public void testIfVectorIs0True() {
        Vector3 vector = new Vector3(0, 0, 0);
        assertThat(vector.isZero(), is(true));
    }

    @Test
    public void testIfVectorIs0False() {
        Vector3 vector = new Vector3(0, 4, 0);
        assertThat(vector.isZero(), is(false));
    }
    
    @Test
    public void testDotProductOfTwo0Vectors() {
        Vector3 vector1 = new Vector3(0, 0, 0);
        Vector3 vector2 = new Vector3(0, 0, 0);

        assertThat(vector1.dot(vector2), is(0d));
    }
    
    @Test
    public void testDotProductTwoVectors() {
        Vector3 vector1 = new Vector3(1, 2, 3);
        Vector3 vector2 = new Vector3(3, 4, 5);

        assertThat(vector1.dot(vector2), is(26d));
    }
    
    @Test
    public void testDotProductOrthogonalVector() {
        Vector3 vector1 = new Vector3(1, 1, 1);
        Vector3 vector2 = new Vector3(1, 4, -5);

        assertThat(vector1.dot(vector2), is(0d));
    }
    
    @Test
    public void lengthOfVector0Is0() {
        Vector3 zero = new Vector3(0,0, 0);

        assertThat(zero.norm(), is(0d));
    }
    
    @Test
    public void lengthOfUnitVectorIs1() {
        Vector3 v = new Vector3(0,1,0);
        assertThat(v.norm(), is(1d));
    }
    
    @Test
    public void lengthOfVectorProduceGoodResultForVector() {
        Vector3 v = new Vector3(5,9,1);
        assertThat(v.norm(), closeTo(10.344080, 1e-6));
    }
    
    @Test
    public void lengthOfNormalizedVectorIs1() {
        Vector3 v = new Vector3(5,9,1);
        assertThat(v.norm(), closeTo(10.344080, 1e-6));
        v = v.normalized();
        assertThat(v.norm(), closeTo(1d, 1e-5));
    }
    
    @Test
    public void lengthOfScaledVectorIsTheScaledTimesBigger() {
        Vector3 v = new Vector3(5,9,1);
        assertThat(v.norm(), closeTo(10.344080, 1e-6));
        v = v.scaled(10);
        assertThat(v.norm(), closeTo(103.44080, 1e-5));
    }

    @Test
    public void crossProductOfParallelVectorIs0() {
        Vector3 v1 = new Vector3(1,2,3);
        Vector3 v2 = new Vector3(4,8,12);
        Vector3 v3 = v1.cross(v2);
        assertThat(v3, is(new Vector3(0, 0, 0)));
    }

    @Test
    public void crossProductOfVectorIsPerpendicularToBaseVector() {
        Vector3 v1 = new Vector3(1, 2, 3);
        Vector3 v2 = new Vector3(4, 5, 17);
        Vector3 v3 = v1.cross(v2);

        assertThat(v1.dot(v3), is(0d));
        assertThat(v2.dot(v3), is(0d));
    }

    @Test
    public void crossProductArgumentShouldNotBeNull() {
        Vector3 v = new Vector3(0, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> v.cross(null));
    }

    @Test
    public void dotProductArgumentShouldNotBeNull() {
        Vector3 v = new Vector3(0, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> v.dot(null));
    }
}
