import model.FactorialJob;
import producer.JobProducer;
import worker.JobWorker;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<FactorialJob> queue = new LinkedBlockingQueue<>(10);

        JobProducer jobProducer1 = new JobProducer(queue,5);
        JobProducer jobProducer2 = new JobProducer(queue,5);

        JobWorker jobWorker1 = new JobWorker(queue);
        JobWorker jobWorker2= new JobWorker(queue);

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<?> f1 = pool.submit(jobProducer1);
        Future<?> f2 = pool.submit(jobProducer2);
        pool.submit(jobWorker1);
        pool.submit(jobWorker2);

        try {
            f1.get();
            f2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println("System shutdown");
    }
}
