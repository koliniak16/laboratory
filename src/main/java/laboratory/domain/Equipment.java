package laboratory.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long equipmentId;
    @Column(nullable = false)
    private String eqName;
    @Column(nullable = false)
    private String type;





    public Equipment(){}

    public Equipment(String eqName, long avaibleAmount, String type){
        super();
        this.eqName = eqName;
        this.type = type;

    }


    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString(){
        return "Equipment [equipmentId=" + equipmentId + ", eqName=" + eqName + ", avaibleAmount=" +"]";
    }



}
