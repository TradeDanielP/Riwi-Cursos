package com.riwi.courses.api.dto.request.create;

import com.riwi.courses.api.dto.request.update.LessonUpdateRequest;

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
public class LessonsRequest extends LessonUpdateRequest {

    @NotNull(message = "Se requiere la identificación del curso")
    @Min(value = 1, message = "El id del curso debe ser mayor que 0")
    private Long courseId;

}
