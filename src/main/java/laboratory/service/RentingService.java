package laboratory.service;




import laboratory.domain.Renting;
import laboratory.repository.RentingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class RentingService {

    @Autowired
    private RentingRepository rentingRepository;
    @Autowired
    private EquipmentService equipmentService;

    public List<Renting> getAllRentings() {
        List<Renting> rentings = new ArrayList<>();
        rentingRepository.findAll()
                .forEach(rentings::add);
        return rentings;
    }


    public Vector createDataVector(){
        Vector vectorRows = new Vector();
        List<Renting> rentings = getAllRentings();
        for(Renting renting : rentings){
            Vector<Object> row = new Vector<>();
            row.addElement(String.valueOf(renting.getRentingNr()));
            row.addElement(renting.getRentingDate());
            row.addElement(renting.getReturnDate());
            row.addElement(String.valueOf(renting.getClient().getClientId()));
            row.addElement(String.valueOf(renting.getWorker().getWorkerId()));
            vectorRows.addElement(row);
        }
        return vectorRows;
    }

    public Renting getRenting(Long id){
        return rentingRepository.findOne(id);
    }

    public void addRenting(Renting renting){
        rentingRepository.save(renting);
    }

    public void updateRenting(Long id, Renting renting){
        rentingRepository.save(renting);
    }

    public void deleteRenting(Long id){
        rentingRepository.delete(id);
    }

}