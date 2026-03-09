package service;

import model.FactorialJob;

import java.util.concurrent.ConcurrentHashMap;

public class ResultStore {
    private final ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String, Object>();

    public void store( String jobId, Object obj){
        map.put( jobId, obj);
    }
    public Object get(String jobID){
        return map.get(jobID);
    }
}
