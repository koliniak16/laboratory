package laboratory.repository;

import laboratory.domain.Renting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentingRepository extends CrudRepository<Renting, Long> {



}
