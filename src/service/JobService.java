package service;

import model.Job;

import java.util.concurrent.BlockingQueue;

public class JobService {
    private final BlockingQueue<Job> queue;

    public JobService(BlockingQueue<Job> queue){
        this.queue = queue;
    }

    public String submit(Job job){
        try {
            queue.put(job);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return job.getId();
    }

}
