import java.util.concurrent.locks.ReentrantLock;

public class DeadlockTask {
    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Task Thread 1: Holding LockA...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Task Thread 1: Waiting for LockB...");
                synchronized (LockB) {
                    System.out.println("Task Thread 1: Acquired LockB!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Task Thread 2: Holding LockB...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Task Thread 2: Waiting for LockA...");
                synchronized (LockA) {
                    System.out.println("Task Thread 2: Acquired LockA!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}