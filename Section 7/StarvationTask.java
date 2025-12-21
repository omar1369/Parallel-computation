public class StarvationTask {
    private static final Object lock = new Object();
    private static int resource = 0;
    private static boolean running = true;

    public static void main(String[] args) {
        Thread greedy = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    resource++;
                    System.out.println("Task Greedy thread incremented resource: " + resource);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
            running = false;
        });
        greedy.setPriority(Thread.MAX_PRIORITY);

        Thread starving = new Thread(() -> {
            while (running) {
                synchronized (lock) {
                    System.out.println("Task Starving thread sees resource: " + resource);
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        starving.setPriority(Thread.MIN_PRIORITY);

        greedy.start();
        starving.start();
    }
}