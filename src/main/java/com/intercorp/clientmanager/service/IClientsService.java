package com.intercorp.clientmanager.service;

import com.intercorp.clientmanager.domain.Cliente;

import java.util.List;
import java.util.Map;

public interface IClientsService {
    Cliente save(Cliente client);

    List<Cliente> getClientsWithProbablyDeathDate();

    Map calculateKpi();
}
