package com.intercorp.clientmanager.service;

import com.intercorp.clientmanager.dao.IClientsDao;
import com.intercorp.clientmanager.domain.Cliente;
import com.intercorp.clientmanager.utils.StatisticsHelperMethods;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientsService implements IClientsService, IDeathDateCalculator {

    private static final int LIFE_EXPECTANCY_IN_DAYS = 27894; //76.42 años

    private IClientsDao clientsDao;

    public ClientsService(IClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    @Override
    public Cliente save(Cliente client) {
        return clientsDao.save(client);
    }

    @Override
    public List<Cliente> getClientsWithProbablyDeathDate() {
        List<Cliente> allClients = clientsDao.findAll();
        allClients
                .forEach(cliente -> cliente.setProbablyDeathDate(calculateDeathDate(cliente.getBirthDate())));
        return allClients;
    }

    @Override
    public LocalDate calculateDeathDate(LocalDate birthDate) {
        return LocalDate
                .now()
                .plusDays(LIFE_EXPECTANCY_IN_DAYS - ChronoUnit.DAYS.between(birthDate, LocalDate.now()) + randomizeLifeExpectancyBasedOnMonth(birthDate));
    }

    @Override
    public Map<String, Double> calculateKpi() {
        HashMap<String, Double> mapsOfKpiValues = new HashMap<>();
        final List<Cliente> allClients = clientsDao.findAll();
        if (allClients.isEmpty()) return mapsOfKpiValues;
        final List<Integer> allAges = allClients
                .stream()
                .mapToInt(Cliente::getAge)
                .boxed()
                .collect(Collectors.toList());
        Double ageAverage = StatisticsHelperMethods
                .calculateAverage(allAges);
        Double standarDeviation = StatisticsHelperMethods
                .calculateStandardDeviation(allAges);

        mapsOfKpiValues.put("promedioEdad", ageAverage);
        mapsOfKpiValues.put("desviacionEstandar", standarDeviation);
        return mapsOfKpiValues;
    }

    private int randomizeLifeExpectancyBasedOnMonth(LocalDate localDate) {
        //método para agregar aleatoreidad a las fechas de muerte
        switch (localDate.getMonthValue()) {
            case 1:
                return 2045;
            case 2:
                return -205;
            case 3:
                return 1605;
            case 4:
                return 490;
            case 5:
                return -102;
            case 6:
                return 341;
            case 7:
                return 99;
            case 8:
                return -1023;
            case 9:
                return 1923;
            case 10:
                return 200;
            case 11:
                return 0;
            default:
                return -121;
        }
    }

}
