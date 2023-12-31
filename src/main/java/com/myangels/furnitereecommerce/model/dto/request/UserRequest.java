package com.myangels.furnitereecommerce.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    String fullName;
    String email;
    String password;

}
