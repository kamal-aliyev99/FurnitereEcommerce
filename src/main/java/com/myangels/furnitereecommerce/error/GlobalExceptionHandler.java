package com.myangels.furnitereecommerce.error;

import com.myangels.furnitereecommerce.error.response.ErrorResponse;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.myangels.furnitereecommerce.util.RequestUtil.getRequestPath;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalExceptionHandler {
    MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        String traceId = UUID.randomUUID().toString();
        LocalDateTime timestamp = LocalDateTime.now();
        log.error("Exception occured,traceId {},message {}, code {}, timestamp {}",
                traceId, ex.getMessage(), ErrorCodes.INTERNAL_SERVER_ERROR, timestamp);

        return ErrorResponse.builder()
                .traceId(traceId)
                .message(ex.getMessage())
                .code(ErrorCodes.INTERNAL_SERVER_ERROR.code())
                .path(getRequestPath())
                .timestamp(timestamp)
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        String traceId = UUID.randomUUID().toString();
        LocalDateTime timestamp = LocalDateTime.now();
        log.error("Exception occured,traceId {},message {}, code {}, timestamp {}",
                traceId, "Not found", ErrorCodes.NOT_FOUND, timestamp);

        return ErrorResponse.builder()
                .traceId(traceId)
                .message(ex.getMessage())
                .code(ErrorCodes.NOT_FOUND.code())
                .path(getRequestPath())
                .timestamp(timestamp)
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValueError> errors = fieldErrors.stream()
                .map(this::buildValueError)
                .collect(Collectors.toList());

        String traceId = UUID.randomUUID().toString();
        LocalDateTime timestamp = LocalDateTime.now();
        log.error("Exception occured,traceId {},message {}, code {}, timestamp {}",
                traceId, "Invalid Arguments", ErrorCodes.BAD_REQUEST, timestamp);

        return ErrorResponse.builder()
                .traceId(traceId)
                .message("Invalid Arguments")
                .code(ErrorCodes.INTERNAL_SERVER_ERROR.code())
                .errors(errors)
                .path(getRequestPath())
                .timestamp(timestamp)
                .build();
    }

    private ValueError buildValueError(FieldError error) {
        return ValueError.builder()
                .message(getErrorMessage(error.getDefaultMessage()))
                .path(error.getObjectName().concat(".").concat(error.getField()))
                .property(error.getField())
                .build();
    }

    private String getErrorMessage(String key) {
        try {
            return messageSource.getMessage(key, null, Locale.ENGLISH);
        } catch (Exception ex) {
            return key;
        }
    }

}
