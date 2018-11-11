package com.gol.core.exeptions;

public class IllegalBoardArraySizeException extends Exception {

    private static final String ILLEGAL_ARRAY_LENGTH_EXCEPTION_MESSAGE = "Expected length is %s, but one of the board arrays" +
            " length was %s.\n The board should be array with an equal dimensions (e.g., 3x3 or 4x4)";

    public IllegalBoardArraySizeException(int boardCapacityFactor, int boardArraySize) {
        this(String.format(ILLEGAL_ARRAY_LENGTH_EXCEPTION_MESSAGE,
                String.valueOf(boardCapacityFactor), String.valueOf(boardArraySize)));
    }

    private IllegalBoardArraySizeException(String message) {
        super(message);
    }
}
