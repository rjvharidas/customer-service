package com.tle.bootcamp.customerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private @NotNull String errorCode;
    private @NotNull String description;
}
