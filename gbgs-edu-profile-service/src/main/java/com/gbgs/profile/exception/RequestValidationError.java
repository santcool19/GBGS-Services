package com.gbgs.profile.exception;

import com.gbgs.profile.exception.template.ResponseEntityException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RequestValidationError implements ResponseEntityException {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    RequestValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
