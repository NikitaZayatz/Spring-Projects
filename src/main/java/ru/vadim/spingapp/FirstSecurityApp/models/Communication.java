package ru.vadim.spingapp.FirstSecurityApp.models;


import javax.persistence.*;

@Entity
@Table(name = "communication")
public class Communication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")

    private Person person;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    public Communication() {
    }

    public Communication(Person person, String question, String answer) {
        this.person = person;
        this.question = question;
        this.answer = answer;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
