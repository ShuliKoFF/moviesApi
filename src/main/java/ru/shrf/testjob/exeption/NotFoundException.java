package ru.shrf.testjob.exeption;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(message);
    }
}
