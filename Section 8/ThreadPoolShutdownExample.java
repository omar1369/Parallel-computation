import java.util.concurrent.*;

public class ThreadPoolShutdownExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Shutdown Task " + taskId + " running in " + Thread.currentThread().getName());
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            });
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            } else {
                System.out.println("All tasks finished.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}