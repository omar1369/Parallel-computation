import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolRunnableExample {
    static class MyTask implements Runnable {
        private final int id;
        public MyTask(int id) { this.id = id; }
        public void run() {
            System.out.println("Runnable Task " + id + " is running in " + Thread.currentThread().getName());
            try { Thread.sleep(400); } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            executor.submit(new MyTask(i));
        }
        executor.shutdown();
    }
}