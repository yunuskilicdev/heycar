package com.kilic.yunus.heycar.exception;

/**
 * Yunus Kilic
 */
public class ErrorMessage {

    public static final String DEALER_NOT_EXISTED_MESSAGE = "Entered dealerId does not exist";
    public static final String INTERNAL_ERROR_MESSAGE = "Unknown error occurred, please try later";

    private ErrorMessage() {
        throw new IllegalStateException("Utility class");
    }
}
