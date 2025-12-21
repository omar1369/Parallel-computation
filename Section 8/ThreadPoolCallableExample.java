import java.util.concurrent.*;

public class ThreadPoolCallableExample {
    static class MyCallable implements Callable<String> {
        private final int id;
        public MyCallable(int id) { this.id = id; }
        public String call() throws Exception {
            Thread.sleep(300);
            return "Callable Task " + id + " completed by " + Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String>[] results = new Future[3];
        for (int i = 0; i < 3; i++) {
            results[i] = executor.submit(new MyCallable(i));
        }
        for (Future<String> result : results) {
            System.out.println(result.get());
        }
        executor.shutdown();
    }
}