package lv.ctco.student;

import com.sun.javafx.beans.IDProperty;
import lv.ctco.assigment.Assignment;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;


@Entity
public class Student implements Serializable {
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Assignment> listAssignment =new  ArrayList<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    public Student() {
    }

    public Student(String surname, String name) {
        this.surname = surname;
        this.name = name;
        this.id = count.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Assignment> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(List<Assignment> listAssignment) {
        this.listAssignment = listAssignment;
    }

    public void addAssigment(Assignment assignment){
        listAssignment.add(assignment);
    }
}
