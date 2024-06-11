package com.riwi.courses.api.dto.request.create;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {

    @NotNull(message = "Se requiere ID de usuario")
    @Min(value = 1, message = "El ID de usuario debe ser mayor que 0")
    private Long userId;

    @NotNull(message = "Se requiere la identificación del curso")
    @Min(value = 1, message = "El id del curso debe ser mayor que 0")
    private Long courseId;
    
}
