package com.example.OnlineBookstore.dto.register;


import com.example.OnlineBookstore.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDTO {
    private String userName;
    private String email;
    private Role role;
}
