package threadpool;

import java.util.concurrent.*;

public class FutureExample2 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Step 1: Callable for registering student
        Callable<Student> registerTask = () -> {
            System.out.println("started registering student ......");
            Thread.sleep(2000); // Simulate delay
            return new Student(1, "Rock", 20);
        };

        // Submit registration task and get Future
        Future<Student> futureStudent = pool.submit(registerTask);

        try {
            // Wait for registration to complete
            Student student = futureStudent.get();
            System.out.println("student registered: " + student.getName());

            // Step 2: Runnable to insert student (after registration)
            Runnable insertTask = () -> {
                System.out.println("inserting student details ......");
//                try {
//                    Thread.sleep(2000); // Simulate DB insert delay
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("student " + student.getName() + " inserted successfully");
            };

            // Submit insert task
            pool.submit(insertTask);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown(); // Always shutdown the pool
        }

        System.out.println("main thread completed tasks");
    }
}

class Student {
    private int id;
    private String name;
    private int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}
