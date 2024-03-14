package ru.vadim.spingapp.FirstSecurityApp.models;




import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @MapsId
    private Person person;

    @Column(name = "location_from")
    private String location_from;

    @Column(name = "location_to")
    private String location_to;

    @Column(name = "phone_number")
    private String phone_number;

    public Transfer() {
    }

    public Transfer(Person person, String location_from, String location_to, String phone_number) {
        this.person = person;
        this.location_from = location_from;
        this.location_to = location_to;
        this.phone_number = phone_number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getLocation_from() {
        return location_from;
    }

    public void setLocation_from(String location_from) {
        this.location_from = location_from;
    }

    public String getLocation_to() {
        return location_to;
    }

    public void setLocation_to(String location_to) {
        this.location_to = location_to;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
