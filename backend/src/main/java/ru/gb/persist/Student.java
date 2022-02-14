package ru.gb.persist;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "age")
    private int age;

    public Student(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname= firstname;
    }

    public Student(int id, String lastname, String firstname, int age) {
        this.id =id;
        this.lastname = lastname;
        this.firstname=firstname;
        this.age = age;
    }

    public Student() {

    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getAge() {
        return age;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastname='" + lastname +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                '}';
    }

}
