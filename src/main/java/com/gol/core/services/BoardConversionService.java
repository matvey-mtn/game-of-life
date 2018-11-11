package com.gol.core.services;

import com.gol.core.constants.GameOfLifeConstants;

import java.util.Arrays;

public class BoardConversionService {

    private static final int DEFAULT_NEIGHBOURS_COUNT = 8;

    public static int[][] convertToNeighborsMatrix(byte[][] boardState, int boardCapacityFactor) {
        int[][] neighborsMatrix = new int[boardCapacityFactor][boardCapacityFactor];

        for (int y = 0; y < boardCapacityFactor; y++) {
            for (int x = 0; x < boardCapacityFactor; x++) {
                if (boardState[y][x] == GameOfLifeConstants.ALIVE) {
                    incrementNeighborsCount(neighborsMatrix, y, x);
                }
            }
        }

        return neighborsMatrix;
    }

    private static void incrementNeighborsCount(int[][] neighborsMatrix, int y, int x) {
        Cell[] neighborsCells = getNeighborCells(new Cell(y, x), neighborsMatrix.length);

        for (Cell cell : neighborsCells) {
            neighborsMatrix[cell.y_axis][cell.x_axis]++;
        }
    }

    private static Cell[] getNeighborCells(Cell cell, int matrixSize) {
        if (cell.x_axis == 0 && cell.y_axis == 0) {
            return new Cell[]{
                    new Cell(0, 1),
                    new Cell(1, 1),
                    new Cell(1, 0)
            };
        }

        Cell[] allNeighborsCells = new Cell[DEFAULT_NEIGHBOURS_COUNT + 1];

        byte neighborArrayIndex = 0;

        // get all possible neighbours
        for (int y = cell.y_axis - 1; y <= cell.y_axis + 1; y++) {
            for (int x = cell.x_axis - 1; x <= cell.x_axis + 1; x++) {
                allNeighborsCells[neighborArrayIndex++] = new Cell(y, x);
            }
        }

        return Arrays.stream(allNeighborsCells)
                // do not include original cell
                .filter(neighbor -> !(neighbor.y_axis == cell.y_axis && neighbor.x_axis == cell.x_axis))
                // do not include neighbors which are outside the board
                .filter(neighbor -> neighbor.y_axis >= 0 && neighbor.x_axis >= 0)
                .filter(neighbor -> neighbor.y_axis < matrixSize && neighbor.x_axis < matrixSize)
                .toArray(Cell[]::new);
    }

    private static class Cell {
        int y_axis;
        int x_axis;

        Cell(int y_axis, int x_axis) {
            this.y_axis = y_axis;
            this.x_axis = x_axis;
        }
    }
}

