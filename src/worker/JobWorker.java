package worker;

import model.FactorialJob;
import model.Job;
import service.ResultStore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class JobWorker implements Runnable{

    private BlockingQueue<Job> queue;
    private ResultStore resultStore;
    private Semaphore semaphore;

    public JobWorker(BlockingQueue<Job> queue , ResultStore resultStore ,  Semaphore semaphore){
        this.resultStore =resultStore;
        this.queue =queue;
        this.semaphore =semaphore;
    }

    @Override
    public void run() {
        try{
            semaphore.acquire();
            while(true){
                Job job = queue.take();
                Object obj = job.execute();
                String str = job.getId();
                 resultStore.store(str , obj);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        semaphore.release();
    }
}
