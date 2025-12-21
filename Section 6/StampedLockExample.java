import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    private static int sharedData = 0;
    private static StampedLock lock = new StampedLock();

    public static void writeData(int value) {
        long stamp = lock.writeLock();
        try {
            sharedData = value;
            System.out.println("Wrote: " + value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static int readData() {
        long stamp = lock.tryOptimisticRead();
        int value = sharedData;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                value = sharedData;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return value;
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

        Thread reader = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Read: " + readData());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}