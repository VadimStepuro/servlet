package org.example.controller;


import org.example.model.Worker;
import org.example.service.WorkerService;

import java.util.List;

public class WorkerController {

    private WorkerService service = new WorkerService();

    public List<Worker> getAll(){
        return service.getAll();
    }

    public Worker save(Worker worker) {
        return service.save(worker);
    }
}
