package Lab_MultiExecutor;

import java.util.List;

// From Section 3: Lab Exercise
public class MultiExecutor {

    // The list of tasks to run
    private List<Runnable> tasks;

    /**
     * Constructor: Receives a list of Runnable tasks.
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method: Starts all tasks, each in its own separate thread.
     */
    public void executeAll() {
        // Loop through the list of tasks
        for (Runnable task : tasks) {
            // Create a new thread for *each* task
            Thread t = new Thread(task);
            // Start the thread
            t.start();
        }
    }
}