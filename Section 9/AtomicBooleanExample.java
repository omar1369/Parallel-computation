import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(false);
        Thread t1 = new Thread(() -> {
            flag.set(true);
            System.out.println("Thread 1 set flag to true");
        });
        Thread t2 = new Thread(() -> {
            if (flag.get()) {
                System.out.println("Thread 2 sees flag as true");
            } else {
                System.out.println("Thread 2 sees flag as false");
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}