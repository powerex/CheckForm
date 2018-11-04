import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceDrawerTest {

    @Test
    public void map() {

        assertEquals(SpaceDrawer.map(3, 2, 5, 4, 10), 6, 0.001);

    }
}