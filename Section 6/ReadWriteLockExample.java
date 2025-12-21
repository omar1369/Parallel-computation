import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static int sharedData = 0;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void writeData(int value) {
        lock.writeLock().lock();
        try {
            sharedData = value;
            System.out.println("Wrote: " + value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static int readData() {
        lock.readLock().lock();
        try {
            return sharedData;
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                writeData(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader1 read: " + readData());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Reader2 read: " + readData());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader1.start();
        reader2.start();

        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}