package com.intercorp.clientmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private int age;
    @Column
    private LocalDate birthDate;

    private LocalDate probablyDeathDate;

    public Cliente(final String name, final String lastName, final Integer age, final LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Cliente(){};

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String apellido) {
        this.lastName = apellido;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int edad) {
        this.age = edad;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate fechaNacimiento) {
        this.birthDate = fechaNacimiento;
    }

    public LocalDate getProbablyDeathDate() {
        return probablyDeathDate;
    }

    public void setProbablyDeathDate(LocalDate probablyDeathDate) {
        this.probablyDeathDate = probablyDeathDate;
    }
}
