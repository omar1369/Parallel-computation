package Section4;

    public class Worker extends Thread {
    public void run() {
        // We put the risky code inside a try-catch block to handle exceptions
        try {
            System.out.println(Thread.currentThread().getName() + " started.");
            int result = 10 / 0; // This will throw an ArithmeticException
            System.out.println(Thread.currentThread().getName() + " computed result: " + result);
            System.out.println(Thread.currentThread().getName() + " finished.");
        } catch (Exception e) {
            // This block executes *only* if an exception happens
            System.out.println(Thread.currentThread().getName() + " caught: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " has completed its run method.");
    }
}
