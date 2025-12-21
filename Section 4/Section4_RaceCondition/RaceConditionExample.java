package Section4_RaceCondition;

public class RaceConditionExample {
    // This is the shared resource
    static int counter = 0; 

    public static void main(String[] args) {
        // Create two tasks that share the same 'counter'
        Thread t1 = new Thread(new MyTask(), "Thread-1");
        Thread t2 = new Thread(new MyTask(), "Thread-2");

        t1.start();
        t2.start();
    }

    static class MyTask implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
                // 1. Read the current value
                int current = counter;

                // 2. Small delay to make the race condition happen more often
                try {
                    Thread.sleep(10); // Pauses the thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 3. Update the value
                int updated = current + 1;
                counter = updated; // This is the 'write' operation

                // Print what happened
                System.out.println(Thread.currentThread().getName()
                        + " -> Current: " + current + ", Updated: " + updated);
            }
        }
    }
}
