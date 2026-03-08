package model;

public class FactorialJob implements Job {
    private final int factorial;

    public FactorialJob(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public void execute() {
        System.out.println("FactorialJob executing...");
        int result = 1;
        for (int i = 1; i <=factorial ; i++) {
            result *= i;
        }
        System.out.println(result);
    }
}
