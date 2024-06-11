package com.riwi.courses.api.dto.request.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "El nombre de usuario no debe ser nulo")
    @Size(
            max = 50,
            message = "El nombre de usuario no puede tener más de 50 caracteres."
    )
    private String userName;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
    private String password;

    @Email(message = "El correo electrónico debe ser válido [ejemplo@gmail.com]")
    private String email;

    @NotBlank(message = "El nombre completo no debe ser nulo")
    @Size(
            max = 100,
            message = "El nombre completo no puede tener más de 100 caracteres."
    )
    private String fullName;
    
}

