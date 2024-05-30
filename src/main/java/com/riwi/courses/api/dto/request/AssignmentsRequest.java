package com.riwi.courses.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentsRequest {
    
    @NotBlank(message = "el titulo del entregable es obligatorio")
    private String assignmentTitle;

    private String description;

    @Future(message = "la fecha debe ser futura")
    @NotNull(message = "la fecha de entrega es obligatoria")
    private LocalDate dueDate;

    @NotNull(message = "la leccion a la que esta asignada este entregable es obligatoria")
    @Min(value = 1, message = "el id de la leccion debe ser mayor a 0")
    private int lessonId;

}
