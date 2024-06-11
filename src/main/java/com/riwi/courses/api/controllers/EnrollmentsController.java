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
import org.springframework.web.bind.annotation.RestController;

import com.riwi.courses.api.dto.request.create.EnrollmentRequest;
import com.riwi.courses.api.dto.response.EnrollmentResponse;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.infraestructure.abstract_services.IEnrollmentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/enrollments")
@AllArgsConstructor
@Tag(name = "Enrollments")
public class EnrollmentsController {

    private final IEnrollmentsService service;

    @Operation(summary = "Haz una Inscripcion", description = "Crea una inscripcion se debe enviar la informacion en formato JSON")
    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(
            @Validated @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Lista una inscripcion por ID", description = "Se debe enviar el ID de la incripcion a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @Operation(summary = "Lista todas las inscripciones de un usuario", description = "Devuelve todas las inscripciones de un usuario específico. Se puede utilizar paginación")
    @GetMapping(path = "/users/{userId}/courses")
    public ResponseEntity<Page<EnrollmentResponse>> getByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.findByUserId(userId, page, size));
    }

    @Operation(summary = "Lista todos los usuarios de un curso", description = "Devuelve todos los usuarios inscritos en un curso específico. Se puede utilizar paginación.")
    @GetMapping(path = "/courses/{courseId}/users")
    public ResponseEntity<Page<UsersResponse>> getUsersByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.findByCourseId(courseId, page, size));
    }

    @Operation(summary = "Elimina una inscripcion", description = "Se debe enviar el ID de la inscripcion a eliminar por URL")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}