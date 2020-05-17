package lesson7;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        RunTests.start(TestAnnotation.class);
    }
}
