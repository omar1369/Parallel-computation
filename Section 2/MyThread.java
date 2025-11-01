public class MyThread extends Thread {
    @Override
    public void run() {
        // This is the code that will execute in the new thread
        System.out.println("Hello from MyThread");
    }
}
