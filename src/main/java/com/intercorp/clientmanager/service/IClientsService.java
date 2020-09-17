package com.intercorp.clientmanager.service;

import com.intercorp.clientmanager.domain.Cliente;

import java.util.List;

public interface IClientsService {

    List <Cliente> getClientsWithProbablyDeathDate();

    Double calculateAgeAverage();

}
