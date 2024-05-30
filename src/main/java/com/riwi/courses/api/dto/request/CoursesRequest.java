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
public class CoursesRequest {
    
    @NotBlank(message = "el nombre del curso es obligatorio")
    @Size(min = 3,max = 100, message = "el nombre del curso debe tener entre 3 y 100 caracteres")
    private String courseName;

    private String description;

    @NotNull(message = "el instructor es obligatorio para crear un curso")
    @Min(value = 1, message = "el id debe ser mayor a 0")
    private int instructorId;

}
