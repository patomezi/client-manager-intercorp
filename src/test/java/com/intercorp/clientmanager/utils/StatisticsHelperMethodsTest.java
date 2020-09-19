package com.intercorp.clientmanager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class StatisticsHelperMethodsTest {

    @Test
    void average_of_empty_array_is_0() {
        Assertions.assertEquals(0.0, StatisticsHelperMethods.calculateAverage(Collections.EMPTY_LIST));
    }

    @Test
    void average_of_single_number_is_same_number_as_double() {
        Integer randomInt = new Random().nextInt();
        Assertions.assertEquals(randomInt.doubleValue(), StatisticsHelperMethods.calculateAverage(Collections.singletonList(randomInt)));
    }

    @Test
    void average_of_20_30_and_40_is_30_as_double() {
        Assertions.assertEquals(30.0, StatisticsHelperMethods.calculateAverage(Arrays.asList(20, 30, 40)));
    }

    @Test
    void standard_deviation_of_single_number_is_zero() {
        Assertions.assertEquals(0.0, StatisticsHelperMethods.calculateStandardDeviation(Collections.singletonList(1)));
    }

    @Test
    void standard_deviation_of_same_number_list_is_zero() {
        Assertions.assertEquals(0.0, StatisticsHelperMethods.calculateStandardDeviation(Arrays.asList(1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    void standard_deviation_of_4_9_11_12_17_5_8_12_14_is_3dot9377878103709665() {
        Assertions.assertEquals(3.9377878103709665, StatisticsHelperMethods.calculateStandardDeviation(Arrays.asList(4,9,11,12,17,5,8,12,14)));
    }

    @Test
    void standard_deviation_of_6_2_3_1_is_1dot87() {
        Assertions.assertEquals(1.8708286933869707,  StatisticsHelperMethods.calculateStandardDeviation(Arrays.asList(6,2,3,1)));
    }

}
