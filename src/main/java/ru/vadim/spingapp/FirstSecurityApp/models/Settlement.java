package ru.vadim.spingapp.FirstSecurityApp.models;


import javax.persistence.*;

@Entity
@Table(name = "settlement")
public class Settlement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @MapsId
    private Person person;

    @Column(name = "arrival_date")
    private String arrival_date;

    @Column(name = "arrival_time")
    private String arrival_time;

    @Column(name = "type")
    private String type;

    @Column(name = "numberofadults")
    private String numberofadults;

    @Column(name = "numberofchildrens")
    private String numberofchildrens;

    public Settlement() {
    }

    public Settlement(Person person, String arrival_date, String arrival_time, String type, String numberofadults, String numberofchildrens) {
        this.person = person;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.type = type;
        this.numberofadults = numberofadults;
        this.numberofchildrens = numberofchildrens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberofadults() {
        return numberofadults;
    }

    public void setNumberofadults(String numberofadults) {
        this.numberofadults = numberofadults;
    }

    public String getNumberofchildrens() {
        return numberofchildrens;
    }

    public void setNumberofchildrens(String numberofchildrens) {
        this.numberofchildrens = numberofchildrens;
    }
}
