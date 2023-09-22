package com.myangels.furnitereecommerce.error;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ValueError  implements Serializable {
    String property;
    String path;
    String message;

}
