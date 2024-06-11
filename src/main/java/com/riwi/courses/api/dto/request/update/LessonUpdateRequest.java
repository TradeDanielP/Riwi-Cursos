package com.riwi.courses.api.dto.request.update;

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
public class LessonUpdateRequest {

    @NotBlank(message = "El título de la lección no debe ser nulo")
    @Size(
            max = 100,
            message = "El título de la lección no puede tener más de 100 caracteres."
    )
    private String lessonTitle;

    @NotBlank(message = "El título del contenido no debe ser nulo")
    private String content;
    
}
