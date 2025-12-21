package Section3;

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            // This is the code the thread will run
            try {
                // We make the thread sleep so we can check its state
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 1. State: NEW
        // The thread has been created but not yet started.
        System.out.println("Before start(): " + t1.getState());

        // 2. State: RUNNABLE
        // Start the thread, which moves it to the RUNNABLE state.
        t1.start();
        System.out.println("After start(): " + t1.getState());

        // 3. State: TERMINATED
        // We wait for the thread to finish its work (the sleep)
        t1.join();
        // After it has finished, its state is TERMINATED.
        System.out.println("After completion: " + t1.getState());
}
}