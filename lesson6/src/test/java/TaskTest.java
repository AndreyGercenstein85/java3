
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import task1.Task;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Enclosed.class)
public class TaskTest {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Task.class);

    @RunWith(Parameterized.class)
    public static class Task1 {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{1, 2, 3, 4, 1}, new int[]{1}},
                    {new int[]{6, 5, 4, 2, 3, 4, 1, 3}, new int[]{1, 3}},
                    {new int[]{9, 4, 5, 5}, new int[]{5, 5}},
                    {new int[]{1, 2, 4}, new int[0]}
            });
        }
    @Parameterized.Parameter(0)
        public int[] numArr;

    @Parameterized.Parameter(1)
        public int[] resNumArr;

        @Test
        public void testcreateNewArr(){
            log.info(": send Array: "+ Arrays.toString(numArr)+" receive Array: "+ Arrays.toString(resNumArr));
            assertArrayEquals("The array we specified must match the received", resNumArr, Task.createNewArr(numArr));
        }
    }

    public static class Task1TestException{
        @Test(expected = RuntimeException.class)
        public void testcreateNewArr() {
            log.info("Check RuntimeException");
            int[] numArr = {1, 1, 1};
            Task.createNewArr(numArr);
        }
    }

    @RunWith(Parameterized.class)
    public static class Task2 {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{3, 3, 4}, true},
                    {new int[]{2, 4, 3}, true},
                    {new int[]{1, 1, 4}, false},
                    {new int[]{1, 1, 1}, false},
                    {new int[]{4, 4, 4}, false}
            });
        }
        @Parameterized.Parameter(0)
        public int[] numArr;

        @Parameterized.Parameter(1)
        public boolean result;

        @Test
        public void testcheckNumInArr(){
            assertEquals(result, Task.checkNumInArr(numArr));
        }
    }

}
