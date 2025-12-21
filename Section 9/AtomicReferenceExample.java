import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<>("Initial");
        Thread t1 = new Thread(() -> {
            ref.set("Thread 1");
            System.out.println("Thread 1 set value: " + ref.get());
        });
        Thread t2 = new Thread(() -> {
            ref.set("Thread 2");
            System.out.println("Thread 2 set value: " + ref.get());
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final value: " + ref.get());
    }
}