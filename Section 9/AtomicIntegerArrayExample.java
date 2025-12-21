import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray arr = new AtomicIntegerArray(5);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < arr.length(); i++) {
                arr.set(i, i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < arr.length(); i++) {
                arr.addAndGet(i, 10);
            }
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        for (int i = 0; i < arr.length(); i++) {
            System.out.println("arr[" + i + "] = " + arr.get(i));
        }
    }
}