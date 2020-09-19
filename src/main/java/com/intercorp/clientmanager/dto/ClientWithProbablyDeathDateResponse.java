package com.intercorp.clientmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.intercorp.clientmanager.domain.Cliente;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientWithProbablyDeathDateResponse {

    String id;
    String nombre;
    String apellido;
    int edad;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate fechaDeNacimiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate fechaProbableDeMuerte;

    public static ResponseEntity from(List<Cliente> clientsWithProbablyDeathDate) {
        List<ClientWithProbablyDeathDateResponse> list = new ArrayList<>();
        clientsWithProbablyDeathDate.forEach(cliente -> list.add(from(cliente)));
        return   clientsWithProbablyDeathDate.isEmpty() ?
                ResponseEntity
                        .noContent()
                        .build() :
                ResponseEntity
                        .ok(list);
    }

    private static ClientWithProbablyDeathDateResponse from(Cliente cliente) {
        return new ClientWithProbablyDeathDateResponse(cliente.getId().toString(), cliente.getName(), cliente.getLastName(), cliente.getAge(), cliente.getBirthDate(), cliente.getProbablyDeathDate());
    }



    public ClientWithProbablyDeathDateResponse(String id, String nombre, String apellido, int edad, LocalDate fechaDeNacimiento, LocalDate fechaProbableDeMuerte) {
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
