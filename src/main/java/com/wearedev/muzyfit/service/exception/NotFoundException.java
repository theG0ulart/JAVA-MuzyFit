package com.wearedev.muzyfit.service.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("Resource Not Found.");
    }
}
