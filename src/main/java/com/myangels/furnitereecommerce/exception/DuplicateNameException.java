package com.myangels.furnitereecommerce.exception;

import com.myangels.furnitereecommerce.error.ErrorCode;
import com.myangels.furnitereecommerce.error.ErrorCodes;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class DuplicateNameException extends RuntimeException{
    String message;
    String code;

    public static DuplicateNameException of(String message, ErrorCode code) {
        var exception = new DuplicateNameException();
        exception.setMessage(message);
        exception.setCode(code.code());
        return exception;
    }

    public static DuplicateNameException of(ErrorCode code) {
        var exception = new DuplicateNameException();
        exception.setCode(code.code());
        exception.setMessage(code.message());
        return exception;
    }

    public static DuplicateNameException fromException(Exception ex) {
        var exception = new DuplicateNameException();
        exception.setMessage(ex.getMessage());
        exception.setCode(ErrorCodes.BAD_REQUEST.code());
        return exception;
    }
}
