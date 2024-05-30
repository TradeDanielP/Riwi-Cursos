package com.riwi.courses.api.dto.request;

import com.riwi.courses.util.enums.ROLE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {
    
    @NotBlank(message = "El Usuario es requerido")
    @Size(min = 1,max = 50,message = "el usuario debe tener entre 1 y 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 1,max = 255,message = "la contraseña debe tener entre 1 y 255 caracteres")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "el nombre completo es obligatorio")
    @Size(min = 1, max = 100, message = "el nombre debe tener entre 1 y 100 caracteres")
    private String fullName;

    @NotNull(message = "el rol es obligatorio")
    private ROLE role;
}
