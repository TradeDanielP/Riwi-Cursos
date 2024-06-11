package com.riwi.courses.api.dto.request.create;


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
public class MessagesRequest {

    @NotNull(message = "Se requiere lel ID del remitente")
    @Min(value = 1, message = "El ID del remitente debe ser mayor que 0")
    private Long senderId;

    @NotNull(message = "Se requiere el ID del receptor")
    @Min(value = 1, message = "El ID del receptor debe ser mayor que 0")
    private Long receiverId;

    @NotNull(message = "Se requiere el ID del curso")
    @Min(value = 1, message = "El id del curso debe ser mayor que 0")
    private Long courseId;

    @NotBlank(message = "El contenido del mensaje es obligatorio")
    private String messageContent;
    
}