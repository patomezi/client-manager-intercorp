package com.intercorp.clientmanager.service;

import java.util.List;
import java.util.Optional;

public interface IStandarDeviationCalculator {

    Optional<Float> calculate(List<Integer> list);

}
