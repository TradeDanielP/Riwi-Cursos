package com.riwi.courses.api.dto.request;

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
    
    @NotNull(message = "el usuario es obligatorio para realizar una inscripcion")
    @Min(value = 1,message = "el id debe ser mayor a 0")
    private int userId;
    @NotNull(message = "el curso es obligatorio para realizar una inscripcion")
    @Min(value = 1,message = "el id debe ser mayor a 0")
    private int courseId;

}
