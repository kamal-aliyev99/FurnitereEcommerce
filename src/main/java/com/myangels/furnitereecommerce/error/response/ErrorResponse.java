package com.myangels.furnitereecommerce.error.response;

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
    LocalDateTime timestamp;
    List<ValueError> errors;

}
