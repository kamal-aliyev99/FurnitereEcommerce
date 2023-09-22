package com.myangels.furnitereecommerce.exception;

import com.myangels.furnitereecommerce.error.ErrorCode;
import com.myangels.furnitereecommerce.error.ErrorCodes;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotFoundException extends RuntimeException {
    String message;
    String code;

    public static NotFoundException of(String message, ErrorCode code) {
        NotFoundException exception = new NotFoundException();
        exception.setMessage(message);
        exception.setCode(code.code());
        return exception;
    }

    public static NotFoundException of(ErrorCode code) {
        NotFoundException exception = new NotFoundException();
        exception.setCode(code.code());
        exception.setMessage(code.message());
        return exception;
    }

    public static NotFoundException fromException(Exception ex) {
        NotFoundException exception = new NotFoundException();
        exception.setMessage(ex.getMessage());
        exception.setCode(ErrorCodes.BAD_REQUEST.code());
        return exception;
    }

}
