package laboratory.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long clientId;
    @Column(nullable = false, length = 11, unique = true )
    private String pesel;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;




    public Client(){}

    public Client(String pesel, String surname, String name){
        super();
        this.pesel = pesel;
        this.surname = surname;
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long idClient) {
        this.clientId = idClient;
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
        return "Client [idClient=" + clientId + ", pesel=" + pesel + ", surname=" + surname + ", name=" + name + "]";
    }


}
