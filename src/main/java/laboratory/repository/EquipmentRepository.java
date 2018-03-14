package laboratory.repository;


import laboratory.domain.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    List<Equipment> findAllByEqName(String name);


}