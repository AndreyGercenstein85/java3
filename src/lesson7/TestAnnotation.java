package lesson7;

public class TestAnnotation {
    @BeforeSuite
    public void runBeforeClass(){
        System.out.println("runBeforeClass()");
    }

    @Test(Test.Priority.ONE)
    public void testONE(){
        System.out.println("ONE");
    }

    @Test(Test.Priority.TEN)
    public void testTEN(){
        System.out.println("TEN");
    }

    @Test(Test.Priority.FOUR)
    public void testFOUR(){
        System.out.println("FOUR");
    }

    @Test(Test.Priority.THREE)
    public void testTHREE(){
        System.out.println("THREE");
    }
    @Test(Test.Priority.SIX)
    public void testSIX(){
        System.out.println("SIX");
    }

    @AfterSuite
    public void runAfterClass() {
        System.out.println("runAfterClass()");
    }
}
