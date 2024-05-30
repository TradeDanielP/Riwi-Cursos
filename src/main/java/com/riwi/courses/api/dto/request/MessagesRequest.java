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
public class MessagesRequest {

    @NotNull(message = "el emisor del mensaje es obligatorio")
    @Min(value = 1, message = "el id del emisor debe ser mayor a 0")
    private int senderId;

    @NotNull(message = "el receptor del mensaje es obligatorio")
    @Min(value = 1, message = "el id del receptor es obligatorio")
    private int receiverId;

    @NotNull(message = "el curso donde esta este mensaje es obligatorio")
    @Min(value = 1, message = "el id del curso es obligatorio")
    private int courseId;

    @NotBlank(message = "el mensaje no puede estar vacio")
    @Size(min = 1, message = "el mensaje debe tener al menos un caracter")
    private String messageContent;
    
}
