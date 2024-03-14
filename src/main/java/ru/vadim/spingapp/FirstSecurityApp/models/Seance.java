package ru.vadim.spingapp.FirstSecurityApp.models;



import javax.persistence.*;

@Entity
@Table(name = "Seance")
public
class Seance {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "procedure_id",referencedColumnName = "id")
    private Procedure procedure;

    @Column(name = "seance_date")
    private String seance_date;

    @Column(name = "seance_time")
    private String seance_time;

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

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public String getSeance_date() {
        return seance_date;
    }

    public void setSeance_date(String seance_date) {
        this.seance_date = seance_date;
    }

    public String getSeance_time() {
        return seance_time;
    }

    public void setSeance_time(String seance_time) {
        this.seance_time = seance_time;
    }

    public Seance() {
    }

    public Seance(Person person, Procedure procedure, String seance_date, String seance_time) {
        this.person = person;
        this.procedure = procedure;
        this.seance_date = seance_date;
        this.seance_time = seance_time;
    }


}