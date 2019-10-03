package com.celcsa.payroll.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * BadResponse
 */
@Data
@Getter
@Setter
@Builder
public class BadResponse {

    private String message;

}