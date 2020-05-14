package task1;

//2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//        Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
//        идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
//        иначе в методе необходимо выбросить RuntimeException.
//        Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

//3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
//        то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).

import java.util.Arrays;


public class Task {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Task.class.getName());
    final static int NUM = 4;

    public static void main(String[] args) {
        //final int[] numArr = createNewArr(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7});
       // log.info(Arrays.toString(numArr));
        //final boolean numArrc = checkNumInArr(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7});
       // log.info(Boolean.toString(numArrc));

       // final int[] numArr2 = createNewArr(new int[]{1, 2, 4, 4, 4, 3, 3, 1, 4});
       // log.info(Arrays.toString(numArr2));
        //final int[] numArr3 = createNewArr(new int[]{1, 1});
        boolean numArrc3 = checkNumInArr(new int[]{1, 1});
        log.info(Boolean.toString(numArrc3));
    }
//2
    public static int[] createNewArr(int[] numArr){
        int[] newNumArr;
       if (numArr.length == 0){
            log.info("numArr is empty!");
            return numArr;
       }
        for (int i = numArr.length - 1; i >= 0 ; i--) {
            if (numArr[i] == NUM) {
                newNumArr = Arrays.copyOfRange(numArr,i + 1, numArr.length);
                return newNumArr;
            }
        }
       throw new RuntimeException("There is no " + NUM + " in the array");
    }
//3
    public static boolean checkNumInArr(int[] numArr) {
        for (int i : numArr) {
            if(i != 1 && i != 4 ) return false;
        }
        return true;
    }

}
