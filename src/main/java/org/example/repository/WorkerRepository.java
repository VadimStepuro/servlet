package org.example.repository;

import org.example.model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkerRepository {

    private List<Worker> workers = new ArrayList<>(){{
            add(new Worker(1, "Vadim", "Stepuro", "1111"));
            add(new Worker(2, "Konstantin", "Belov", "2222"));
            add(new Worker(3, "Ilia", "Primak", "3333"));
            add(new Worker(4, "Anton", "Borodich", "4444"));}};
    public List<Worker> getWorkers(){
        return workers;
    }

    public Worker addWorker(Worker worker){

        workers.add(worker);

        return worker;
    }

    public Worker getById(int id){

        for(int i = 0; i < workers.size(); i++){

            if(workers.get(i).getId() == id)
                return workers.get(i);
        }

        throw new NoSuchElementException("No such worker");
    }

    public void delete(int id){

        for(int i = 0; i < workers.size(); i++){

            if(workers.get(i).getId() == id) {
                workers.remove(i);
                return;
            }
        }

        throw new NoSuchElementException("No such worker");
    }
}
