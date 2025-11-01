public class MyTask implements Runnable {
    @Override
    public void run() {
        // This is the code that will execute in the new thread
        System.out.println("Hello from MyTask");
    }
}
