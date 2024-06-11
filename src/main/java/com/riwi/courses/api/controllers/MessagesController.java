package com.riwi.courses.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.courses.api.dto.request.create.MessagesRequest;
import com.riwi.courses.api.dto.response.MessagesResponse;
import com.riwi.courses.infraestructure.services.MessagesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
@Tag(name = "Messages")
public class MessagesController {

    @Autowired
    private MessagesService service;

    @Operation(summary = "Crea un nuevo mensaje", description = "Crea un mensaje se debe enviar la informacion en formato JSON")
    @PostMapping
    public ResponseEntity<MessagesResponse> createMessage(
            @Validated @RequestBody MessagesRequest request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Lista todos los mensajes de un curso", description = "Devuelve todos los mensajes de un curso específico. Se puede utilizar paginación")
    @GetMapping("/course/{courseId}/messages")
    public ResponseEntity<Page<MessagesResponse>> getMessagesByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.findByCourseId(courseId, page, size));
    }

    @Operation(summary = "Lista todos los mensajes entre dos usuarios especificos", description = "Devuelve todos los mensajes de 2 usuarios específicos. Se puede utilizar paginación")
    @GetMapping("/sender/{senderId}/receiver/{receiverId}")
    public ResponseEntity<Page<MessagesResponse>> getMessagesBySenderAndReceiverId(
            @PathVariable Long senderId,
            @PathVariable Long receiverId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.findBySenderAndReceiverId(senderId, receiverId, page, size));
    }
}
