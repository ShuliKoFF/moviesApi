package ru.shrf.testjob.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shrf.testjob.dto.ExeptionResponsDTO;
import ru.shrf.testjob.exeption.BusinessException;
import javax.validation.ValidationException;


@ControllerAdvice
@Slf4j
@ResponseBody
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExeptionResponsDTO> handleBusinessException(BusinessException e) {
        ExeptionResponsDTO errorResponse = new ExeptionResponsDTO("INTERNAL_ERROR", e.getMessage());
        log.error("Business error", e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExeptionResponsDTO> handleValidationException(ValidationException e) {
        ExeptionResponsDTO errorResponse = new ExeptionResponsDTO("INTERNAL_ERROR", e.getMessage());
        log.error("Validation error", e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExeptionResponsDTO> handleValidationException(Exception e) {
        ExeptionResponsDTO errorResponse = new ExeptionResponsDTO("INTERNAL_ERROR", e.getMessage());
        log.error("Application error", e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
