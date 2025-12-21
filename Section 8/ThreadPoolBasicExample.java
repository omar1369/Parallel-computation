public class ThreadPoolBasicExample {
    public static void main(String[] args) {
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running in " + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            });
        }
        executor.shutdown();
    }
}