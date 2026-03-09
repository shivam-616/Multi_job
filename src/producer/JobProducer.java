package producer;

import model.FactorialJob;
import model.Job;

import java.util.concurrent.BlockingQueue;

public class JobProducer implements Runnable {
   private BlockingQueue<Job> queue;
   private final int noofJob;

   public JobProducer(BlockingQueue<Job> queue ,int noofJob) {
        this.queue = queue;
        this.noofJob = noofJob;
   }
   @Override
   public void run() {
       for(int i = 1; i<=noofJob; i++){
           try{
               queue.put(new FactorialJob(i));
           }catch(InterruptedException e){
               e.printStackTrace();
           }
       }
   }
}
