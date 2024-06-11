package com.riwi.courses.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.courses.api.dto.request.create.LessonsRequest;
import com.riwi.courses.api.dto.request.update.LessonUpdateRequest;
import com.riwi.courses.api.dto.response.LessonsResponse;
import com.riwi.courses.infraestructure.abstract_services.ILessonsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
@Tag(name = "Lessons", description = "Operaciones relacionadas con las lecciones de los cursos")
public class LessonsController {

    private final ILessonsService service;

    @Operation(summary = "Crear una lección", description = "Crea una nueva lección en un curso. La información debe enviarse en formato JSON.")
    @PostMapping
    public ResponseEntity<LessonsResponse> create(
            @Validated @RequestBody LessonsRequest request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Obtener una lección por ID", description = "Obtiene los detalles de una lección específica por su ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonsResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @Operation(summary = "Listar todas las lecciones", description = "Devuelve todas las lecciones, con soporte para paginación.")
    @GetMapping
    public ResponseEntity<Page<LessonsResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.getAll(page, size));
    }

    @Operation(summary = "Actualizar una lección", description = "Actualiza los detalles de una lección específica por su ID.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonsResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody LessonUpdateRequest request) {
        return ResponseEntity.ok(this.service.update(id, request));
    }

    @Operation(summary = "Eliminar una lección", description = "Elimina una lección específica por su ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
