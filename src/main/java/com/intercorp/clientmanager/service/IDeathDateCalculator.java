package com.intercorp.clientmanager.service;

import java.time.LocalDate;

public interface IDeathDateCalculator {

    LocalDate calculateDeathDate(LocalDate birthDate);

}
