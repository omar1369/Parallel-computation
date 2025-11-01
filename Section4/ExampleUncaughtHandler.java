package Section4;

    class WorkerThread extends Thread {
    public void run() {
        // No try-catch here
        System.out.println("Thread started: " + getName());
        int divisor = Integer.parseInt("0"); // compute at runtime so it's not a compile-time constant
        int x = 10 / divisor; // This throws an ArithmeticException at runtime
        System.out.println("Result: " + x);
        System.out.println("This line will never be reached.");
    }
}

public class ExampleUncaughtHandler {
    public static void main(String[] args) {
        WorkerThread t1 = new WorkerThread();

        // This is the handler. It's code that will run
        // when the thread 't1' throws an uncaught exception.
        t1.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("--- Handler ---");
            System.out.println("Exception in " + thread.getName() + ": " +
                    exception.getMessage());
            System.out.println("--- End of Handler ---");
        });

        // Start the thread
        t1.start();
    }
}

