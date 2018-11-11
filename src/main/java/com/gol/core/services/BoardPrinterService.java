package com.gol.core.services;

import com.gol.core.constants.GameOfLifeConstants;
import com.gol.core.exeptions.IllegalBoardArraySizeException;

/**
 * Board is always 2 dimensional array with y-axis going down and x-axis going right ( -> ).
 * The starting point of the array (0,0) is in the upper-left corner of the matrix.
 **/

public class BoardPrinterService {

    private static final String DEAD_CELL = "[ ]";
    private static final String LIVING_CELL = "[X]";

    private int boardCapacityFactor;

    public BoardPrinterService(int boardCapacityFactor) {
        this.boardCapacityFactor = boardCapacityFactor;
    }

    public void printBoard(byte[][] boardState) throws IllegalBoardArraySizeException {

        validateBoardArraySize(boardState);

        for (int y_axis = 0; y_axis < boardCapacityFactor; y_axis++) {
            for (int x_axis = 0; x_axis < boardCapacityFactor; x_axis++) {
                if (boardState[y_axis][x_axis] == GameOfLifeConstants.ALIVE) {
                    System.out.print(LIVING_CELL);
                } else {
                    System.out.print(DEAD_CELL);
                }
            }
            System.out.print("\n");
        }

    }

    public void printBoardRaw(byte[][] boardState) {

        for (int y_axis = 0; y_axis < boardCapacityFactor; y_axis++) {
            for (int x_axis = 0; x_axis < boardCapacityFactor; x_axis++) {
                System.out.print(String.format("[%s]", boardState[y_axis][x_axis]));
            }
            System.out.print("\n");
        }

    }

    public void printNeighboursMatrix(int[][] neighborsMatrix) throws IllegalBoardArraySizeException {

        validateNeighborsMatrixSize(neighborsMatrix);

        for (int y_axis = 0; y_axis < boardCapacityFactor; y_axis++) {
            for (int x_axis = 0; x_axis < boardCapacityFactor; x_axis++) {
                System.out.print(String.format("[%s]", neighborsMatrix[y_axis][x_axis]));
            }
            System.out.print("\n");
        }

    }


    public int getBoardCapacityFactor() {
        return boardCapacityFactor;
    }

    private void validateBoardArraySize(byte[][] boardState) throws IllegalBoardArraySizeException {
        int boardArrayLength = boardState.length;

        if (boardArrayLength != boardCapacityFactor) {
            throw new IllegalBoardArraySizeException(boardCapacityFactor, boardArrayLength);
        }
        for (byte[] boardStateSubArray : boardState) {
            int subArrayLength = boardStateSubArray.length;
            if (subArrayLength != boardCapacityFactor) {
                throw new IllegalBoardArraySizeException(boardCapacityFactor, subArrayLength);
            }
        }
    }

    private void validateNeighborsMatrixSize(int[][] neighborsMatrix) throws IllegalBoardArraySizeException {
        int boardArrayLength = neighborsMatrix.length;

        if (boardArrayLength != boardCapacityFactor) {
            throw new IllegalBoardArraySizeException(boardCapacityFactor, boardArrayLength);
        }
        for (int[] neighborsMatrixSubArray : neighborsMatrix) {
            int subArrayLength = neighborsMatrixSubArray.length;
            if (subArrayLength != boardCapacityFactor) {
                throw new IllegalBoardArraySizeException(boardCapacityFactor, subArrayLength);
            }
        }
    }
}
