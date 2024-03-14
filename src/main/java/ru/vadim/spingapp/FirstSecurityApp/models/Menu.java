package ru.vadim.spingapp.FirstSecurityApp.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dish_name")
    private String dish_name;

    @Column(name = "dish_description")
    private String dish_description;

    @Column(name="dish_price")
    private int dish_price;


    @Column(name = "image_url")
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_description() {
        return dish_description;
    }

    public void setDish_description(String dish_description) {
        this.dish_description = dish_description;
    }

    public int getDish_price() {
        return dish_price;
    }

    public void setDish_price(int dish_price) {
        this.dish_price = dish_price;
    }


    @ManyToMany(mappedBy = "menu")
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Menu() {
    }

    public Menu(String dish_name, String dish_description, int dish_price,String image_url) {
        this.dish_name = dish_name;
        this.dish_description = dish_description;
        this.dish_price = dish_price;
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dish_name='" + dish_name + '\'' +
                ", dish_description='" + dish_description + '\'' +
                ", dish_price=" + dish_price +
                '}';
    }
}
