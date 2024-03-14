package ru.vadim.spingapp.FirstSecurityApp.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medical_procedures_list")
public class Procedure {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "procedure_name")
    private String location_from;

    @Column(name = "procedure_time")
    private String procedure_time;

    @Column(name = "doctor")
    private String doctor;

    @Column(name ="procedure_cost")
    private String procedure_cost;

    @Column(name = "isavaliable")
    private boolean isavaliable;


    public Procedure() {
    }

    public Procedure(String location_from, String procedure_time, String doctor, String procedure_cost, boolean isavaliable) {
        this.location_from = location_from;
        this.procedure_time = procedure_time;
        this.doctor = doctor;
        this.procedure_cost = procedure_cost;
        this.isavaliable = isavaliable;
    }

    public String getProcedure_cost() {
        return procedure_cost;
    }

    public void setProcedure_cost(String procedure_cost) {
        this.procedure_cost = procedure_cost;
    }

    public boolean isIsavaliable() {
        return isavaliable;
    }

    public void setIsavaliable(boolean isavaliable) {
        this.isavaliable = isavaliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @OneToMany(mappedBy = "procedure")
    List<Seance> seances;

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

    public String getLocation_from() {
        return location_from;
    }

    public void setLocation_from(String location_from) {
        this.location_from = location_from;
    }

    public String getProcedure_time() {
        return procedure_time;
    }

    public void setProcedure_time(String procedure_time) {
        this.procedure_time = procedure_time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

}
