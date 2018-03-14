package laboratory.service;




import laboratory.domain.Equipment;
import laboratory.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        List<Equipment> equipments = new ArrayList<>();
        equipmentRepository.findAll()
                .forEach(equipments::add);
        return equipments;
    }



    public Vector getEquipmentByName(String name){

        Vector namesList = new Vector();
        List<Equipment> equipments = equipmentRepository.findAllByEqName(name);

        for(Equipment equipment : equipments){
            Vector<Object> row = new Vector<>();
            row.addElement(equipment.getEquipmentId());
            row.addElement(equipment.getEqName());
            row.addElement(equipment.getType());
            namesList.addElement(row);
        }
        return namesList;
    }

    public Vector createDataVector(){
        Vector vectorRows = new Vector();
        List<Equipment> equipments = getAllEquipment();
        for(Equipment equipment : equipments){
            Vector<Object> row = new Vector<>();
            row.addElement(String.valueOf(equipment.getEquipmentId()));
            row.addElement(equipment.getEqName());
            row.addElement(equipment.getType());
            vectorRows.addElement(row);
        }
        return vectorRows;
    }




    public List<Equipment> getEquipmentByIds(String equipmentId){
        List<Equipment> equipmentList = new ArrayList<>();
        String[] ids = equipmentId.split(",");
        for(String id : ids){
            Equipment equipment = equipmentRepository.findOne(Long.parseLong(id));
            equipmentList.add(equipment);
        }
        return equipmentList;
    }



    public Equipment getEquipment(Long id){
        return equipmentRepository.findOne(id);
    }

    public void addEquipment(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public void updateEquipment(Long id, Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id){
        equipmentRepository.delete(id);
    }

}
