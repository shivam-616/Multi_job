package model;

import java.util.UUID;

public class FactorialJob implements Job {
    private final int factorial;
    private final String id;

    public FactorialJob(int factorial) {
        this.factorial = factorial;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object execute() {
        int result = 1;
        int i = 1;
        for (i = 1; i <= factorial; i++) {
            result *= i;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        .out.println("Factorial of " + factorial + " = " + result + "  -- Thread name is " + Thread.currentThread().getName());
           return result;
    }
}
