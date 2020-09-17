package com.intercorp.clientmanager.utils;

import java.util.List;

public class StatisticsHelperMethods {
    public static Double calculateAverage(List<Integer> numbers) {
        return numbers
                .stream()
                .mapToInt(number -> number.intValue())
                .average()
                .orElse(0.0);
    }
}
