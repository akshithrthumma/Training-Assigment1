/*Akshith Thumma*/
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Threading {
    static Lock lock = new ReentrantLock();
    static int index = 0;
    
    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                    lock.lock();
                    while (index % 3 == 0) {
                        System.out.print("A");
                        index++;
                        i++;
                    }
                    lock.unlock();
                }   
            }
        }       

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                    lock.lock();
                    while (index % 3 == 1) {
                        System.out.print("B");
                        index++;
                        i++;
                    }
                    lock.unlock();
                }
            }
        }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                    lock.lock();
                    while (index % 3 == 2) {
                        System.out.print("C");
                        index++;
                        i++;
                    }
                    lock.unlock();
                }
            }
        }

    public static void main(String[] args) {
        ThreadA tA = new ThreadA();
        ThreadB tB = new ThreadB();
        ThreadC tC = new ThreadC();
        tA.start();
        tB.start();
        tC.start();
    }

}
