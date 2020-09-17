package com.intercorp.clientmanager.domain;

import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;

    @Transient
    private LocalDate probablyDeathDate;

    public Cliente(final String nombre, final String apellido, final Integer edad, final LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(){};

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getProbablyDeathDate() {
        return probablyDeathDate;
    }

    public void setProbablyDeathDate(LocalDate probablyDeathDate) {
        this.probablyDeathDate = probablyDeathDate;
    }
}
