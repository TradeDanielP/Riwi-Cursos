package com.riwi.courses.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.courses.api.dto.request.create.SubmissionsRequest;
import com.riwi.courses.api.dto.request.update.SubmissionUpdateRequest;
import com.riwi.courses.api.dto.response.SubmissionsResponse;
import com.riwi.courses.infraestructure.services.SubmissionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/submissions")
@AllArgsConstructor
@Tag(name = "Submissions")
public class SubmissionsController {

    private final SubmissionsService service;

    @Operation(summary = "Crear una nueva presentación", description = "Crea una nueva presentación enviando la información en formato JSON")
    @PostMapping
    public ResponseEntity<SubmissionsResponse> createSubmission(
            @Validated @RequestBody SubmissionsRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Operation(summary = "Obtener una presentación por ID", description = "Obtiene una presentación específica enviando su ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SubmissionsResponse> getSubmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Actualizar una presentación", description = "Actualiza una presentación existente enviando su ID y la información actualizada en formato JSON")
    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionsResponse> updateSubmission(
            @PathVariable Long id,
            @Validated @RequestBody SubmissionUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminar una presentación", description = "Elimina una presentación existente enviando su ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener todas las presentaciones de una asignación", description = "Obtiene todas las presentaciones de una asignación específica enviando el ID de la asignación")
    @GetMapping(path = "/assignment/{assignmentId}")
    public ResponseEntity<Page<SubmissionsResponse>> getSubmissionsByAssignmentId(
            @PathVariable Long assignmentId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.findByAssignmentId(assignmentId, page, size));
    }

    @Operation(summary = "Obtener todas las presentaciones de un usuario", description = "Obtiene todas las presentaciones de un usuario específico enviando el ID del usuario")
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Page<SubmissionsResponse>> getSubmissionsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.findByUserId(userId, page, size));
    }
}
