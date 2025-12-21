import java.util.concurrent.Semaphore;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private static int[] buffer = new int[BUFFER_SIZE];
    private static int in = 0;
    private static int out = 0;

    private static Semaphore empty = new Semaphore(BUFFER_SIZE);
    private static Semaphore full = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);

    static class Producer implements Runnable {
        public void run() {
            int item = 0;
            while (true) {
                try {
                    empty.acquire();
                    mutex.acquire();

                    // Produce item
                    buffer[in] = item;
                    System.out.println("Producer produced: " + item);
                    in = (in + 1) % BUFFER_SIZE;
                    item++;

                    mutex.release();
                    full.release();

                    Thread.sleep(1000); // Simulate time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            while (true) {
                try {
                    full.acquire();
                    mutex.acquire();

                    // Consume item
                    int item = buffer[out];
                    System.out.println("Consumer consumed: " + item);
                    out = (out + 1) % BUFFER_SIZE;

                    mutex.release();
                    empty.release();

                    Thread.sleep(1500); // Simulate time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();
    }
}