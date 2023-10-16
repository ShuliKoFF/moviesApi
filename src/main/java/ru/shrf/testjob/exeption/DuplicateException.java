package ru.shrf.testjob.exeption;

public class DuplicateException extends BusinessException {

    public DuplicateException(String message) {
        super(message);
    }
}
