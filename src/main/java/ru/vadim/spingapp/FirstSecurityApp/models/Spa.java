package ru.vadim.spingapp.FirstSecurityApp.models;


import javax.persistence.*;

@Entity
@Table(name = "spa")
public class Spa {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private Person person;


    @Column(name = "telephone")
    private String telephone;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "type")
    private String type;

    public Spa() {
    }

    public Spa(Person person, String telephone, String date, String time) {
        this.person = person;
        this.telephone = telephone;
        this.date = date;
        this.time = time;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
