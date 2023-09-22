package com.myangels.furnitereecommerce.error;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCodes implements ErrorCode{
    BAD_REQUEST("Bad Request",400),
    INTERNAL_SERVER_ERROR("Internal Server error",500),
    NOT_FOUND("Not found",404);

    String message;
    Integer  code;

    @Override
    public String code() {
        return this.name();
    }

    @Override
    public String message() {
        return this.message;
    }

}
