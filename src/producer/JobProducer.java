package producer;

import model.FactorialJob;

import java.util.concurrent.BlockingQueue;

public class JobProducer implements Runnable {
   private BlockingQueue<FactorialJob> queue;
   private final int noofJob;

   public JobProducer(BlockingQueue<FactorialJob> queue ,int noofJob) {
        this.queue = queue;
        this.noofJob = noofJob;
   }
   @Override
   public void run() {
       for(int i = 0; i< noofJob; i++){
           try{
               queue.put(new FactorialJob(i));
           }catch(InterruptedException e){
               e.printStackTrace();
           }
       }
   }
}
