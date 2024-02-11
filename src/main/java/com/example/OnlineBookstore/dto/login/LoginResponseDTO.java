package com.example.OnlineBookstore.dto.login;

import com.example.OnlineBookstore.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String userName;
    private String email;
    private Role role;
    private boolean isAuthenticated;
    private String token;
}
