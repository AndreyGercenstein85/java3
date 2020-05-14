package Lesson1;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //1 CHECK
        String[] strArray = new String[2];
        Integer[] intArray = new Integer[2];

        int i = 0;
        int j = 1;
        strArray[i] = "ABC";
        strArray[j] = "DEF";

        intArray[i] = 0;
        intArray[j] = 1;

        System.out.println(Arrays.deepToString(strArray));
        System.out.println(Arrays.deepToString(intArray));

        swap(strArray, i, j);
        swap(intArray, i, j);

        System.out.println(Arrays.deepToString(strArray));
        System.out.println(Arrays.deepToString(intArray));

        //2 CHECK
        String[] strArray2 = {"1", "2", "3", "4"};
        ArrayList<String> strArrayList = convertToArrayList(strArray2);
        System.out.println(strArrayList.toString());

        //3. Большая задача CHECK
        Apple apple1 = new Apple(Apple.WEIGHT);
        Apple apple2 = new Apple(Apple.WEIGHT);

        Orange orange1 = new Orange(Orange.WEIGHT);
        Orange orange2 = new Orange(Orange.WEIGHT);


        Box<Apple> box1 = new Box<>(apple1, apple2);
        Box<Orange> box2 = new Box<>(orange1, orange2);

        System.out.println(box1.compare(box2));

        Box<Orange> box3 = new Box();
        box2.transfer(box3);

        box1.add(new Apple(Apple.WEIGHT));
        box2.remove(orange1);



    }
    //1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static <T> void swap(T[] array, Integer i,
                                Integer j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList convertToArrayList(T[] array){
        return new ArrayList<T>(Arrays.asList(array));
    }


}
