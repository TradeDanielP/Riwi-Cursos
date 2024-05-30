package com.riwi.courses.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class LessonsRequest {

    @NotBlank(message = "el titulo de la leccion es obligatorio")
    @Size(min = 1,max = 100,message = "el titulo debe tener entre 1 y 100 caracteres")
    private String lessonTitle;

    private String content;

    @NotNull(message = "el curso al que esta asignado esta leccion es obligatoria")
    @Min(value = 1, message = "el id del curso debe ser mayor a 0")
    private int courseId;
    
}
