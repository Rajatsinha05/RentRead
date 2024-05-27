package com.RentRead.Exchange.Request;

import com.RentRead.Enum.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String password;
    private Role role;
}
