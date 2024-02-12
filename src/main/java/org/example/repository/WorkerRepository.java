package org.example.repository;

import org.example.model.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkerRepository {

    private List<Worker> workers = Arrays.asList(
            new Worker(1, "Vadim", "Stepuro", "1111"),
            new Worker(2, "Konstantin", "Belov", "2222"),
            new Worker(3, "Ilia", "Primak", "3333"),
            new Worker(4, "Anton", "Borodich", "4444"));
    public List<Worker> getWorkers(){
        return workers;
    }

    public Worker addWorker(Worker worker){
        workers.add(worker);
        return worker;
    }
}
