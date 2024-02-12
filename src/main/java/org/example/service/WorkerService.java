package org.example.service;

import org.example.model.Worker;
import org.example.repository.WorkerRepository;

import java.util.List;

public class WorkerService {
    private final WorkerRepository repository = new WorkerRepository();

    public List<Worker> getAll(){
        return repository.getWorkers();
    }

    public Worker save(Worker worker){
        return repository.addWorker(worker);
    }

    public Worker edit(Worker worker){
        Worker byId = repository.getById(worker.getId());

        byId.setName(worker.getName());
        byId.setSurname(worker.getSurname());
        byId.setPassword(worker.getPassword());

        return byId;
    }

    public void delete(int id){
        repository.delete(id);
    }
}
