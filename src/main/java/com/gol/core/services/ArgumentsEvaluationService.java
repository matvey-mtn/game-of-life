package com.gol.core.services;

import com.gol.core.constants.GameOfLifeConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentsEvaluationService {

    private static final String TRUE_CONSTANT = "true";

    public static Map<Integer, String> evaluateArguments(String[] args) {
        Map<Integer, String> argumentsMap = new HashMap<>();
        updateMapWithDefaultValues(argumentsMap);

        for (String arg : args) {
            if (matchingArgumentDefinition(arg, GameOfLifeConstants.START_ARGUMENT)) {
                argumentsMap.put(GameOfLifeConstants.START_ARGUMENT_ID, TRUE_CONSTANT);
            }

            if (matchingArgumentDefinition(arg, GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT)) {
                String shortForm = getShortForm(GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT);
                String longForm = getLongForm(GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT);
                updateArgumentsMap(argumentsMap, arg, GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT_ARGUMENT_ID, shortForm, longForm);
            }

            if (matchingArgumentDefinition(arg, GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT)) {
                String shortForm = getShortForm(GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT);
                String longForm = getLongForm(GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT);
                updateArgumentsMap(argumentsMap, arg, GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT_ID, shortForm, longForm);
            }

            if (matchingArgumentDefinition(arg, GameOfLifeConstants.CUSTOM_BOARD)) {
                argumentsMap.put(GameOfLifeConstants.CUSTOM_BOARD_ARGUMENT_ID, TRUE_CONSTANT);
            }

            if (matchingArgumentDefinition(arg, GameOfLifeConstants.DEBUG_ARGUMENT)) {
                argumentsMap.put(GameOfLifeConstants.DEBUG_ARGUMENT_ID, TRUE_CONSTANT);
            }
        }

        return argumentsMap;
    }

    private static void updateMapWithDefaultValues(Map<Integer, String> argumentsMap) {
        argumentsMap.put(GameOfLifeConstants.CUSTOM_BOARD_ARGUMENT_ID, "false");
        argumentsMap.put(GameOfLifeConstants.START_ARGUMENT_ID, "false");
        argumentsMap.put(GameOfLifeConstants.DEBUG_ARGUMENT_ID, "false");
        argumentsMap.put(GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT_ARGUMENT_ID, GameOfLifeConstants.DEFAULT_BOARD_CAPACITY_FACTOR);
        argumentsMap.put(GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT_ID, GameOfLifeConstants.DEFAULT_NUMBER_OF_ITERATIONS);
    }

    private static void updateArgumentsMap(Map<Integer, String> argumentMap, String arg, Integer argumentID, String shortForm, String longForm) {
        if (arg.startsWith(shortForm)) {
            String replace = arg.replace(shortForm + "=", "");
            argumentMap.put(argumentID, replace);
        } else {
            String replace = arg.replace(longForm + "=", "");
            argumentMap.put(argumentID, replace);
        }
    }

    private static String getShortForm(List<String> argumentDefinition) {
        return argumentDefinition.get(0);
    }
        private static String getLongForm(List<String> argumentDefinition) {
        return argumentDefinition.get(1);
    }

    private static boolean matchingArgumentDefinition(String input, List<String> argumentDefinition) {
        return argumentDefinition.stream()
                .anyMatch(input::startsWith);
    }

}
