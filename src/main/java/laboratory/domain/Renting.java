package laboratory.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Renting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long rentingNr;
    @Column(nullable = false)@Temporal(TemporalType.DATE)
    private Date rentingDate;
    @Column(nullable = false)@Temporal(TemporalType.DATE)
    private Date returnDate;



    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "workerId")
    private Worker worker;


    public List<Equipment> getRentingPositions() {
        return rentingPositions;
    }

    public void setRentingPositions(List<Equipment> rentingPositions) {
        this.rentingPositions = rentingPositions;
    }

    @ManyToMany(fetch=FetchType.EAGER)

    private List<Equipment> rentingPositions;

    public Renting(){}

    public Renting(Date rentingDate, Date returnDate){
        super();
        this.rentingDate = rentingDate;
        this.returnDate = returnDate;
    }

    public long getRentingNr() {
        return rentingNr;
    }

    public void setRentingNr(long rentingNr) {
        this.rentingNr = rentingNr;
    }

    public Date getRentingDate() {
        return rentingDate;
    }

    public void setRentingDate(Date rentingDate) {
        this.rentingDate = rentingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }



    @Override
    public String toString(){
        return "Renting [rentingNr=" + rentingNr + ", rentingDate=" + rentingDate + ", returnDate=" + returnDate + "]";
    }

}

