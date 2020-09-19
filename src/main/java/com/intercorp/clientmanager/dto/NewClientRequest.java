package com.intercorp.clientmanager.dto;


import com.intercorp.clientmanager.domain.Cliente;
import com.intercorp.clientmanager.validations.ValidClienteRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZoneOffset;

@ValidClienteRequest
public class NewClientRequest {
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String apellido;
    @NotNull
    @Min(value = 18L, message = "la persona debe ser mayor de edad")
    @Max(value = 130L, message = "Supuera la edad máxima permitida")
    private int edad;
    @NotNull
    private Instant fechaDeNacimiento;

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

    public Instant getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Instant fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Cliente toDomain() {
        return new Cliente(nombre, apellido, edad, fechaDeNacimiento.atZone(ZoneOffset.UTC).toLocalDate());
    }
}
