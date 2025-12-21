package Section2_Creation;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        t1.start();

        Runnable task = new MyTask();

        Thread t2 = new Thread(task);
        t2.start();
    }
}
