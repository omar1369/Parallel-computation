package Section4;

public class MultiThreadExceptionExample {
    public static void main(String[] args) {
        // Create three worker threads 
        Worker t1 = new Worker();
        Worker t2 = new Worker();
        Worker t3 = new Worker();

        // Start all three threads 
        t1.start();
        t2.start();
        t3.start();
    }
}
