package com.riwi.courses.api.dto.request.update;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentUpdateRequest {
    @NotBlank(message = "El título de la asignación no debe ser nulo")
    @Size(
            max = 100,
            message = "El título de la asignación no puede tener más de 100 caracteres."
    )
    private String assignmentTitle;

    @NotBlank(message = "La descripción no debe ser nula")
    private String description;

    @NotNull(message = "Se requiere fecha de vencimiento")
    @Future(message = "No es posible introducir una fecha anterior a la fecha actual.")
    private Date dueDate;
}
