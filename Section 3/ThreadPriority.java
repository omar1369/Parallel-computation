package Section3;

public class ThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Thread 1 Priority: " +
                Thread.currentThread().getPriority()), "Priority-Min");

        Thread t2 = new Thread(() -> System.out.println("Thread 2 Priority: " +
                Thread.currentThread().getPriority()), "Priority-Max");

        t1.setPriority(Thread.MIN_PRIORITY); // Set priority to 1 
        t2.setPriority(Thread.MAX_PRIORITY); // Set priority to 10 

        t1.start();
        t2.start();
    }
}
