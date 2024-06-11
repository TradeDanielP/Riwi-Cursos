package com.riwi.courses.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.courses.api.dto.request.create.AssignmentsRequest;
import com.riwi.courses.api.dto.request.update.AssignmentUpdateRequest;
import com.riwi.courses.api.dto.response.AssignmentsResponse;
import com.riwi.courses.infraestructure.services.AssignmentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/assignments")
@AllArgsConstructor
@Tag(name = "Assignments")
public class AssignmentsController {

    private final AssignmentsService service;

    @Operation(summary = "Crear una nueva asignación", description = "Crea una nueva asignación enviando la información en formato JSON")
    @PostMapping
    public ResponseEntity<AssignmentsResponse> createAssignment(
            @Validated @RequestBody AssignmentsRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Operation(summary = "Obtener una asignación por ID", description = "Obtiene una asignación específica enviando su ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssignmentsResponse> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Actualizar una asignación", description = "Actualiza una asignación existente enviando su ID y la información actualizada en formato JSON")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssignmentsResponse> updateAssignment(
            @PathVariable Long id,
            @Validated @RequestBody AssignmentUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Eliminar una asignación", description = "Elimina una asignación existente enviando su ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener todas las asignaciones de una lección", description = "Obtiene todas las asignaciones de una lección específica enviando el ID de la lección")
    @GetMapping(path = "/lesson/{lessonId}/assignments")
    public ResponseEntity<Page<AssignmentsResponse>> getAssignmentsByLessonId(
            @PathVariable Long lessonId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.findByLessonId(lessonId, page, size));
    }
}
