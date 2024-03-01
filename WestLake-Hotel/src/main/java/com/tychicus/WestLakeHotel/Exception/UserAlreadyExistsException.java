package com.tychicus.WestLakeHotel.Exception;

import org.antlr.v4.runtime.RuntimeMetaData;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
