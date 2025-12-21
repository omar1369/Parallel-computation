package Section3;

public class ThreadNaming {
    public static void main(String[] args) {
        Thread t1 = new Thread("My first thread");
        t1.start();
        System.out.println("Started: " + t1.getName());

        Thread t2 = new Thread();
        t2.setName("Worker Thread-1");
        t2.start();
        System.out.println("Started " + t2.getName());

        Runnable task = () -> System.out.println("Running in: " + Thread.currentThread().getName());
        Thread t3 = new Thread(task, "Runnable Thread");
        t3.start();
}
}