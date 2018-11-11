package com.gol.core;

import com.gol.core.constants.GameOfLifeConstants;
import com.gol.core.exeptions.IllegalBoardArraySizeException;
import com.gol.core.services.ArgumentsEvaluationService;
import com.gol.core.services.BoardConversionService;
import com.gol.core.services.BoardPrinterService;
import com.gol.core.services.RulesEnforcementService;

import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> argumentsMap = ArgumentsEvaluationService.evaluateArguments(args);
        String startArgument = argumentsMap.get(GameOfLifeConstants.START_ARGUMENT_ID);
        if (!Boolean.valueOf(startArgument)) {
            printHelpMessage();
        } else {
            String customModeArgument = argumentsMap.get(GameOfLifeConstants.CUSTOM_BOARD_ARGUMENT_ID);
            Boolean customMode = Boolean.valueOf(customModeArgument);
            String debugModeArgument = argumentsMap.get(GameOfLifeConstants.DEBUG_ARGUMENT_ID);
            Boolean debug = Boolean.valueOf(debugModeArgument);

            if (customMode) {
                // todo add custom game
                System.out.println("ERROR: Custom game is not available yet!");
            } else {
                Integer boardCapacityFactor = getBoardCapacityFactor(argumentsMap);
                Integer numberOfIterations = getNumberOfIterations(argumentsMap);
                BoardPrinterService printerService = new BoardPrinterService(boardCapacityFactor);
                byte[][] gameBoard = new byte[boardCapacityFactor][boardCapacityFactor];

                System.out.println("\nINFO: starting the game...");
                long startTime = System.currentTimeMillis();
                generateRandomLiveCells(boardCapacityFactor, gameBoard);

                for (int i = 0; i < numberOfIterations; i++) {
                    System.out.println("\n=================================================================");
                    System.out.println(String.format("Game Iteration #%s", String.valueOf(i + 1)));

                    try {
                        System.out.println("\nBoard State:");
                        printerService.printBoard(gameBoard);

                        int[][] neighborsMatrix = BoardConversionService.convertToNeighborsMatrix(gameBoard, boardCapacityFactor);

                        if (debug) {
                            System.out.println("\nBoard State Raw:");
                            printerService.printBoardRaw(gameBoard);
                            System.out.println("\nBoard Neighbors Matrix:");
                            printerService.printNeighboursMatrix(neighborsMatrix);
                        }

                        gameBoard = RulesEnforcementService.enforceRules(gameBoard, neighborsMatrix, boardCapacityFactor);

                    } catch (IllegalBoardArraySizeException ex) {
                        System.out.println("\nAn exception occurred during program execution");
                        System.out.println(String.format("Error Message:\n %s", ex.getMessage()));
                    } catch (Exception ex) {
                        System.out.println(String.format("\nAn error occurred: %s", ex.getMessage()));
                        System.out.println("\n Board Last State:");
                        printerService.printBoardRaw(gameBoard);
                    }
                }

                long duration = System.currentTimeMillis() - startTime;
                System.out.println(String.format("\n Program finished successfully in %s ms", String.valueOf(duration)));
            }
        }
    }

    private static void printHelpMessage() {
        System.out.println("\nPlease launch program by using the following arguments:");
        GameOfLifeConstants.ARGUMENTS
                .forEach(argument -> {
                    argument.forEach(argumentForm -> System.out.print(argumentForm + " "));
                    if (argument.get(0).equals("-c")) {
                        System.out.print("- this argument using the following format -c=10 or --capacity=10");
                    }
                    if (argument.get(0).equals("-i")) {
                        System.out.print("- this argument using the following format -i=10 or --iterations=10");
                    }
                    System.out.println();
                });
        System.out.println("\n Example: java -jar game-of-life.jar --start --capacity=10 --iterations=100");
    }

    private static void generateRandomLiveCells(int boardCapacityFactor, byte[][] board) {
        for (int y = 0; y < boardCapacityFactor; y++) {
            for (int x = 0; x < boardCapacityFactor; x++) {
                Random rand = new Random();
                board[y][x] = (byte) rand.nextInt(2);
            }
        }
    }

    private static Integer getBoardCapacityFactor(Map<Integer, String> argumentsMap) {
        String factorArgument = argumentsMap.get(GameOfLifeConstants.BOARD_CAPACITY_ARGUMENT_ARGUMENT_ID);
        Integer boardCapacityFactor = Integer.valueOf(factorArgument);
        System.out.println(String.format("INFO: board capacity factor is %s", factorArgument));
        return boardCapacityFactor;
    }

    private static Integer getNumberOfIterations(Map<Integer, String> argumentsMap) {
        String numberOfIterationsArgument = argumentsMap.get(GameOfLifeConstants.NUMBER_OF_ITERATIONS_ARGUMENT_ID);
        Integer numberOfIterations = Integer.valueOf(numberOfIterationsArgument);
        System.out.println(String.format("INFO: number of iterations is %s", numberOfIterations));
        return numberOfIterations;
    }

}
