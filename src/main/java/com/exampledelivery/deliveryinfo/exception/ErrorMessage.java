package com.exampledelivery.deliveryinfo.exception;

import java.io.Serializable;

public interface ErrorMessage extends Serializable {

    String getMessage();
    int getCode();
}
