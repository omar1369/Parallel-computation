package Section3;

public class ThreadTypeDemo {
    public static void main(String[] args) {

        // --- Example 2: Daemon Thread ---
        // A Daemon thread is a background thread. The JVM will not wait for it.
        Thread daemon = new Thread(() -> {
            try {
                // This thread will try to run forever
                while (true) {
                    System.out.println("Daemon thread running...");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                // This will be interrupted when the JVM shuts down
            }
        }, "MyDaemonThread");

        // THIS IS THE IMPORTANT STEP:
        daemon.setDaemon(true); // Mark this thread as a daemon
        daemon.start();

        // --- Example 1: User Thread ---
        // A User thread is a normal, high-priority thread.
        // The JVM *will* wait for this thread to finish.
        Thread user = new Thread(() -> {
            System.out.println("User thread running... and finishing.");
        }, "MyUserThread");

        user.start();

        System.out.println("Main thread is ending.");
        // After this line, the 'main' thread terminates.
        // The 'user' thread will also finish quickly.
        // The JVM will shut down, abruptly killing the 'daemon' thread.
    }
}
