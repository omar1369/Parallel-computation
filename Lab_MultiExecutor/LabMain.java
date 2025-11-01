package Lab_MultiExecutor;

import java.util.ArrayList;
import java.util.List;

// This is the Main class to test your MultiExecutor
public class LabMain {
    public static void main(String[] args) {
        // 1. Create a list of tasks (as Runnables)
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> System.out.println("Task 1 running in: " + Thread.currentThread().getName()));
        tasks.add(() -> System.out.println("Task 2 running in: " + Thread.currentThread().getName()));
        tasks.add(() -> System.out.println("Task 3 running in: " + Thread.currentThread().getName()));

        // 2. Create the MultiExecutor with that list
        MultiExecutor executor = new MultiExecutor(tasks);

        // 3. Tell the executor to run all tasks
        executor.executeAll();
    }
}

