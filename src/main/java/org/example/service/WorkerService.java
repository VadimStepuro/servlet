package org.example.service;

import org.example.model.Worker;
import org.example.repository.WorkerRepository;

import java.util.List;

public class WorkerService {
    private WorkerRepository repository = new WorkerRepository();

    public List<Worker> getAll(){
        return repository.getWorkers();
    }

    public Worker save(Worker worker){
        return repository.addWorker(worker);
    }
}
