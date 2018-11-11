package com.gol.core.constants;

import java.util.Arrays;
import java.util.List;

public interface GameOfLifeConstants {
    byte ALIVE = 1;
    byte DEAD = 0;

    String DEFAULT_BOARD_CAPACITY_FACTOR = "10";
    String MAX_BOARD_CAPACITY_FACTOR = "100";
    String DEFAULT_NUMBER_OF_ITERATIONS = "30";

    List<String> START_ARGUMENT = Arrays.asList("-s", "--start");
    List<String> BOARD_CAPACITY_ARGUMENT = Arrays.asList("-c", "--capacity");
    List<String> NUMBER_OF_ITERATIONS_ARGUMENT = Arrays.asList("-i", "--iterations");
    List<String> CUSTOM_BOARD = Arrays.asList("-ct", "--custom");
    List<String> DEBUG_ARGUMENT = Arrays.asList("-d", "--debug");

    List<List<String>> ARGUMENTS = Arrays.asList(
            START_ARGUMENT,
            BOARD_CAPACITY_ARGUMENT,
            NUMBER_OF_ITERATIONS_ARGUMENT,
            CUSTOM_BOARD,
            DEBUG_ARGUMENT
    );


    Integer START_ARGUMENT_ID = 0;
    Integer BOARD_CAPACITY_ARGUMENT_ARGUMENT_ID = 1;
    Integer NUMBER_OF_ITERATIONS_ARGUMENT_ID = 2;
    Integer CUSTOM_BOARD_ARGUMENT_ID = 3;
    Integer DEBUG_ARGUMENT_ID = 4;

}
