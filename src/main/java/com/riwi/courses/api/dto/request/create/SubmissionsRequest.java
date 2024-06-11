package com.riwi.courses.api.dto.request.create;


import com.riwi.courses.api.dto.request.update.SubmissionUpdateRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionsRequest extends SubmissionUpdateRequest {

    @NotNull(message = "Se requiere ID de usuario")
    @Min(value = 1, message = "El ID de usuario debe ser mayor que 0")
    private Long userId;

    @NotNull(message = "El ID de asignación es obligatorio")
    @Min(value = 1, message = "El id de asignación debe ser mayor que 0")
    private Long assignmentId;

}
