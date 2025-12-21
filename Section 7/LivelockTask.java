import java.util.concurrent.locks.ReentrantLock;

public class LivelockTask {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                if (lock1.tryLock()) {
                    try {
                        Thread.sleep(50);
                        if (lock2.tryLock()) {
                            try {
                                System.out.println("Task Thread 1: Acquired both locks");
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
                attempts++;
            }
        });

        Thread t2 = new Thread(() -> {
            int attempts = 0;
            while (attempts < 10) {
                if (lock2.tryLock()) {
                    try {
                        Thread.sleep(50);
                        if (lock1.tryLock()) {
                            try {
                                System.out.println("Task Thread 2: Acquired both locks");
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
                attempts++;
            }
        });

        t1.start();
        t2.start();
    }
}