package com.riwi.courses.api.dto.request.create;


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
public class CoursesRequest{

    @NotBlank(message = "El nombre del curso no debe ser nulo")
    @Size(
            max = 100,
            message = "El nombre del curso no puede tener más de 100 caracteres."
    )
    private String courseName;

    @NotBlank(message = "la descripción no debe ser nulo")
    private String description;

    @NotNull(message = "Se requiere el id del instructor")
    @Min(value = 1, message = "El ID del instructor debe ser mayor que 0")
    private Long instructorId;

}

