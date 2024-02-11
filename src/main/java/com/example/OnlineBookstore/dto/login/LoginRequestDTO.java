package com.example.OnlineBookstore.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "The userName is required")
    private String userName;
    @NotBlank(message = "The password is required")
    private String password;
}
