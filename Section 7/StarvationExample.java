public class StarvationExample {
    private static final Object lock = new Object();
    private static int resource = 0;

    public static void main(String[] args) {
        // High-priority thread
        Thread greedy = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    resource++;
                    System.out.println("Greedy thread incremented resource: " + resource);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        });
        greedy.setPriority(Thread.MAX_PRIORITY);

        // Low-priority thread
        Thread starving = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("Starving thread sees resource: " + resource);
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        starving.setPriority(Thread.MIN_PRIORITY);

        greedy.start();
        starving.start();
    }
}