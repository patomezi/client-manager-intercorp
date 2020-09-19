package com.intercorp.clientmanager.service;

import com.intercorp.clientmanager.dao.IClientsDao;
import com.intercorp.clientmanager.domain.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientsServiceTest {

    @InjectMocks
    ClientsService clientsService;
    @Mock
    IClientsDao clientsDao;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getClientsWithProbablyDeathDate_should_return_same_list_with_estimated_death_date() {
        List<Cliente> clientsWithoutDeathDate = Collections.singletonList(new Cliente("aClient", "apellido", 28, LocalDate.of(1992, 7, 9)));
        when(clientsDao.findAll()).thenReturn(clientsWithoutDeathDate);
        assertNull(clientsWithoutDeathDate.get(0).getProbablyDeathDate());
        List<Cliente> clientesWithProbablyDeathDate = clientsService.getClientsWithProbablyDeathDate();
        assertNotNull(clientesWithProbablyDeathDate.get(0).getProbablyDeathDate());
    }

    @Test
    void getClientsWithProbablyDeathDate_should_return_empty_list_if_there_is_not_clients_on_databse() {
        when(clientsDao.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.EMPTY_LIST, clientsService.getClientsWithProbablyDeathDate());
    }

    @Test
    void calculateKpi_should_return_a_map_with_age_average_and_standard_deviation(){
        Cliente clientOne = new Cliente ("nombre", "apellido", 28, LocalDate.of(2020,9,07));
        when(clientsDao.findAll()).thenReturn(Collections.singletonList(clientOne));
        assertEquals(28.0, clientsService.calculateKpi().get("promedioEdad"));
        assertEquals(0.0, clientsService.calculateKpi().get("desviacionEstandar"));
    }

}
