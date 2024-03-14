package ru.vadim.spingapp.FirstSecurityApp.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attractionslist")
public class Attraction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "attraction_name")
    private String attraction_name;

    @Column(name = "attraction_description")
    private String attraction_description;


    @Column(name = "image_url")
    private String image_url;

    @ManyToMany(mappedBy = "attractions")
    private List<Person> persons;


    public Attraction() {
    }

    public Attraction(String attraction_name, String attraction_description, String image_url, List<Person> persons) {
        this.attraction_name = attraction_name;
        this.attraction_description = attraction_description;
        this.image_url = image_url;
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", attraction_name='" + attraction_name + '\'' +
                ", attraction_description='" + attraction_description + '\'' +
                ", persons=" + persons +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        this.attraction_name = attraction_name;
    }

    public String getAttraction_description() {
        return attraction_description;
    }

    public void setAttraction_description(String attraction_description) {
        this.attraction_description = attraction_description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
