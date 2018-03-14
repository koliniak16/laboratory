package laboratory.repository;

import laboratory.domain.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Override
    Client findOne(Long aLong);

    Client findOneByPesel(String Pesel);

    @Query("SELECT count(c) FROM Client c")
    int countClients();

}