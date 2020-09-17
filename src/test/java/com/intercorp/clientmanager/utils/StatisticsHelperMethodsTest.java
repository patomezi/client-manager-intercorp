package com.intercorp.clientmanager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class StatisticsHelperMethodsTest {

    @Test
    void average_of_empty_list_is_0(){
        Assertions.assertEquals(0.0f, StatisticsHelperMethods.calculateAverage(Collections.EMPTY_LIST));
    }

    @Test
    void average_of_single_number_is_same_number_as_double(){
        Integer randomInt = new Random().nextInt();
        Assertions.assertEquals(randomInt.doubleValue(), StatisticsHelperMethods.calculateAverage(Collections.singletonList(randomInt)));
    }

    @Test
    void average_of_20_30_and_40_is_30_as_double(){
        Assertions.assertEquals(30.0, StatisticsHelperMethods.calculateAverage(Arrays.asList(20, 30, 40)));
    }



}
