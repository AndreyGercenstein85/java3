package Lesson4;

public class Task implements Runnable {

    private char symbol;
    private final Main lock;
    private int postion;

    public Task(char symbol, Main lock, int postion) {
        this.symbol = symbol;
        this.lock = lock;
        this.postion = postion;
    }

    @Override
    public void run() {

        while (lock.status < 14) {
            synchronized (lock){

                while (!((lock.status % 3) == 0) && postion == 1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while  (!((lock.status % 3) == 1) && postion == 2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while  (!((lock.status % 3) == 2) && postion == 3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(symbol);
                lock.status++;
                lock.notifyAll();
            }
        }
    }
}
