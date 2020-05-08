package Lesson4;

//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.

public class Main {

    volatile  int status = 0;
    public static void main(String[] args) {
        Main lock = new Main();
        Thread thread1 = new Thread(new Task('A', lock, 1));
        Thread thread2 = new Thread(new Task('B', lock, 2));
        Thread thread3 = new Thread(new Task('C', lock, 3));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

