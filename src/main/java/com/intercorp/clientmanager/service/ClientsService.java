package com.intercorp.clientmanager.service;

import com.intercorp.clientmanager.domain.Cliente;
import com.intercorp.clientmanager.utils.StatisticsHelperMethods;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientsService implements IClientsService {

    @Override
    public List<Cliente> getClientsWithProbablyDeathDate() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Double calculateAgeAverage(){
        return StatisticsHelperMethods.calculateAverage(Collections.EMPTY_LIST);
    }

}
