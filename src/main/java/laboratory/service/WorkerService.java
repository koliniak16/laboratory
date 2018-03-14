package laboratory.service;



import laboratory.domain.Client;
import laboratory.domain.Worker;
import laboratory.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        List<Worker> workers = new ArrayList<>();
        workerRepository.findAll()
                .forEach(workers::add);
        return workers;
    }

    public Vector createDataVector(){
        Vector vectorRows = new Vector();
        List<Worker> workers = getAllWorkers();
        for(Worker worker : workers){
            Vector<Object> row = new Vector<>();
            row.addElement(String.valueOf(worker.getWorkerId()));
            row.addElement(worker.getName());
            row.addElement(worker.getSurname());
            row.addElement(worker.getPesel());
            vectorRows.addElement(row);
        }
        return vectorRows;
    }

    public Worker getWorker(Long id){
        return workerRepository.findOne(id);
    }

    public void addWorker(Worker worker){
        workerRepository.save(worker);
    }

    public void updateWorker(Long id, Worker worker){
        workerRepository.save(worker);
    }

    public void deleteWorker(Long id){
        workerRepository.delete(id);
    }

}