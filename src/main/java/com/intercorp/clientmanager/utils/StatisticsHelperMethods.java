package com.intercorp.clientmanager.utils;

import java.util.Collection;

public class StatisticsHelperMethods {

    public static double calculateAverage(Collection<Integer> numbers) {
        return  numbers
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public static double calculateStandardDeviation(Collection<Integer> values) {
        double average = calculateAverage(values);
        double standardDeviation = 0.0;
        for (double num : values) {
            standardDeviation += Math.pow(num - average, 2);
        }

        return Math.sqrt(standardDeviation / values.size());
    }
}
