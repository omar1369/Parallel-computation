public class DefaultHandlerExample {
    public static void main(String[] args) {

        // This is the GLOBAL handler. It will catch uncaught
        // exceptions from *any* thread.
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("--- GLOBAL HANDLER ---");
            System.out.println("Global handler caught exception in: " + thread.getName());
            System.out.println("Error: " + exception.getMessage());
            System.out.println("--- End of Global Handler ---");
        });

        // This thread will crash
        Thread t1 = new Thread(() -> {
            throw new RuntimeException("Thread 1 crashed!");
        }, "Thread-1");

        // This thread will also crash
        Thread t2 = new Thread(() -> {
            throw new ArithmeticException("Thread 2: Division by zero!");
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}