package lesson7;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class RunTests {
    static void start(Class clazz) throws InvocationTargetException, IllegalAccessException {
        List<Method> methodList = Arrays.asList(clazz.getMethods());
        List<Method> setBeforeSuite = methodList.stream()
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null)
                .collect(Collectors.toList());
        if (setBeforeSuite.size() > 1)
            throw new RuntimeException("BeforeSuite annotation должна присутствовать в единственном экземпляре");
        else {
            Method method = setBeforeSuite.get(0);
            method.invoke(new TestAnnotation());
        }

        List<Method> testList = methodList.stream()
                .filter(method -> method.getAnnotation(Test.class) != null)
                .collect(Collectors.toList());
        Map<Method, Test.Priority> methodPriorityMap = new HashMap<>();


        Iterator<Method> iterator = testList.iterator();

        while (iterator.hasNext()){
             Method method = iterator.next();
             Test.Priority priority = method.getAnnotation(Test.class).value();
            methodPriorityMap.put(method, priority);
        }

         Map<Method, Test.Priority> mapSort =  methodPriorityMap.entrySet().stream()
                 .sorted(Map.Entry.comparingByValue()).collect(LinkedHashMap::new,
                         (m, c) -> m.put(c.getKey(), c.getValue()),
                         LinkedHashMap::putAll);

        for (Map.Entry<Method, Test.Priority> entry : mapSort.entrySet()) {
            entry.getKey().invoke(new TestAnnotation());
        }


        List<Method> setAfterSuite = methodList.stream()
                .filter(method -> method.getAnnotation(AfterSuite.class) != null)
                .collect(Collectors.toList());
        if (setAfterSuite.size() > 1)
            throw new RuntimeException("AfterSuite annotation должна присутствовать в единственном экземпляре");
        setAfterSuite.iterator().next().invoke(new TestAnnotation());
    }

}

