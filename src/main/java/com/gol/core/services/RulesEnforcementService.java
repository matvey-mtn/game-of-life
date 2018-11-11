package com.gol.core.services;

import com.gol.core.constants.GameOfLifeConstants;

public class RulesEnforcementService {

    public static byte[][] enforceRules(byte[][] boardState, int[][] neighborsMatrix, int boardCapacityFactor) {
        byte[][] resultState = new byte[boardCapacityFactor][boardCapacityFactor];

        for (int y = 0; y < boardCapacityFactor; y++) {
            for (int x = 0; x < boardCapacityFactor; x++) {
                int neighborsCount = neighborsMatrix[y][x];
                if (neighborsCount == 3) {
                    resultState[y][x] = GameOfLifeConstants.ALIVE;
                } else if(neighborsCount == 2 && boardState[y][x] == GameOfLifeConstants.ALIVE) {
                    resultState[y][x] = GameOfLifeConstants.ALIVE;
                } else if (neighborsCount > 3) {
                    resultState[y][x] = GameOfLifeConstants.DEAD;
                }
            }
        }

        return resultState;
    }
}
