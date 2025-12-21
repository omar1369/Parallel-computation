import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        Thread.sleep(50);
                        if (lock2.tryLock()) {
                            try {
                                System.out.println("Thread 1: Acquired both locks");
                                break;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        Thread.sleep(50);
                        if (lock1.tryLock()) {
                            try {
                                System.out.println("Thread 2: Acquired both locks");
                                break;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}