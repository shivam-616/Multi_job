package worker;

import model.FactorialJob;
import model.Job;

import java.util.concurrent.BlockingQueue;

public class JobWorker implements Runnable {
    private BlockingQueue<FactorialJob> queue;


    public JobWorker(BlockingQueue<FactorialJob> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while(true){
                FactorialJob job = queue.take();
                job.execute();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
