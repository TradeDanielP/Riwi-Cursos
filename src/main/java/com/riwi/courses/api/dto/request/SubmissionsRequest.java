package com.riwi.courses.api.dto.request;

import jakarta.validation.constraints.Min;
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
public class SubmissionsRequest {
    
    private String content;

    @Size(min = 1, max = 5, message = "la nota debe ser entre 1.0 y 5.0")
    private double grade;

    @NotNull(message = "el usuario a cargar esta entrega es obligatorio")
    @Min(value = 1, message = "el id del usuario debe ser mayor a 0")
    private int userId;

    @NotNull(message = "el entregable al cual se subira esta entrega es obligatorio")
    @Min(value = 1, message = "el id del entregable debe ser mayor a 0")    
    private int assignmentId;

}
