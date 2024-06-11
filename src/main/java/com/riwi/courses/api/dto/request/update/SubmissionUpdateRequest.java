package com.riwi.courses.api.dto.request.update;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionUpdateRequest {

    @NotNull(message = "El grado no puede ser nulo")
    @Digits(integer = 1, fraction = 1, message = "El grado debe tener 1 dígito entero y 1 decimal")
    @DecimalMin(value = "1.0", message = "La nota debe ser al menos 1.0")
    @DecimalMax(value = "5.0", message = "La nota debe ser inferior o igual a 5,0")
    private BigDecimal grade;

    @NotBlank(message = "El título del contenido no debe ser nulo")
    private String content;
    
}
