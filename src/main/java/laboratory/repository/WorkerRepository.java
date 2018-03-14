package laboratory.repository;


import laboratory.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {

    @Override
    Worker findOne(Long aLong);
}