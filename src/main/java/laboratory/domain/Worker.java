package laboratory.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long workerId;
    @Column(nullable = false, unique = true, length = 11)
    private String pesel;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;



    public Worker(){};

    public Worker(String pesel, String surname, String name){
        super();
        this.pesel = pesel;
        this.surname = surname;
        this.name = name;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Worker [workerId=" + workerId + ", pesel=" + pesel + ", surname=" + surname + ", name=" + name + "]";
    }

}
