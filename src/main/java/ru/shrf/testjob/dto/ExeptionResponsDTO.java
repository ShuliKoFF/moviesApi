package ru.shrf.testjob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor

public class ExeptionResponsDTO {
    private final String error;
    private final String message;

}
