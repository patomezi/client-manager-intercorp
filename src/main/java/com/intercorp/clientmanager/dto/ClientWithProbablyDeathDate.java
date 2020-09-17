package com.intercorp.clientmanager.dto;

import com.intercorp.clientmanager.domain.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientWithProbablyDeathDate {

    String id;
    String nombre;
    String apellido;
    int edad;
    LocalDate fechaDeNacimiento;
    LocalDate fechaProbableDeMuerte;

    public static List<ClientWithProbablyDeathDate> from(List<Cliente> clientsWithProbablyDeathDate) {
        List<ClientWithProbablyDeathDate> list = new ArrayList<>();
        clientsWithProbablyDeathDate.forEach(cliente -> list.add(from(cliente)));
        return list;
    }

    private static ClientWithProbablyDeathDate from(Cliente cliente) {
        return new ClientWithProbablyDeathDate(cliente.getId().toString(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechaNacimiento(), cliente.getProbablyDeathDate());
    }

    public ClientWithProbablyDeathDate(String id, String nombre, String apellido, int edad, LocalDate fechaDeNacimiento, LocalDate fechaProbableDeMuerte) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaProbableDeMuerte = fechaProbableDeMuerte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDate getFechaProbableDeMuerte() {
        return fechaProbableDeMuerte;
    }

    public void setFechaProbableDeMuerte(LocalDate fechaProbableDeMuerte) {
        this.fechaProbableDeMuerte = fechaProbableDeMuerte;
    }
}
