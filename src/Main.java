import model.FactorialJob;
import model.Job;
import producer.JobProducer;
import service.JobService;
import service.ResultStore;
import worker.JobWorker;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Job> queue = new LinkedBlockingQueue<>(10);
        ResultStore resultStore = new ResultStore();
        JobService jobService = new JobService(queue);
        Semaphore semaphore = new Semaphore(2);

        JobProducer jobProducer1 = new JobProducer(queue, 5);
        JobProducer jobProducer2 = new JobProducer(queue, 5);

        JobWorker jobWorker1 = new JobWorker(queue, resultStore,semaphore);
        JobWorker jobWorker2 = new JobWorker(queue, resultStore,semaphore);

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<?> f1 = pool.submit(jobProducer1);
        Future<?> f2 = pool.submit(jobProducer2);


        pool.submit(jobWorker1);
        pool.submit(jobWorker2);


        ArrayList<String> list = new ArrayList<>();

        list.add(jobService.submit(new FactorialJob(5)));
        list.add(jobService.submit(new FactorialJob(6)));
        list.add(jobService.submit(new FactorialJob(7)));

        Thread.sleep(5000);

        for (String str : list) {
            System.out.println("Result of job " + str + " = " + resultStore.get(str));
        }

        pool.shutdownNow();
        System.out.println("System shutdown");
    }
}
