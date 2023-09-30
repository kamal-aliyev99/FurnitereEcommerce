package com.myangels.furnitereecommerce.error.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myangels.furnitereecommerce.error.ValueError;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse  implements Serializable {
    String traceId;
    String code;
    String message;
    String path;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime timestamp;
    List<ValueError> errors;

}
