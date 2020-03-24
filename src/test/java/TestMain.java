import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestMain {
    @Test
    public void test() throws IOException {
        Main.main(null);
        assertTrue(true);
    }
}
