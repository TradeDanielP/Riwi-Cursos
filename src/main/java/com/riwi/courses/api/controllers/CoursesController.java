package com.riwi.courses.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.courses.api.dto.request.create.CoursesRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;
import com.riwi.courses.infraestructure.abstract_services.ICoursesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
@Tag(name = "Courses")
public class CoursesController {

    private final ICoursesService service;

    @Operation(summary = "Crea un Curso", description = "Crea un Curso se debe enviar la informacion en formato JSON")
    @PostMapping
    public ResponseEntity<CoursesResponse> create(
            @Validated @RequestBody CoursesRequest request) {
        return ResponseEntity.ok(this.service.create(request));
    }

    @Operation(summary = "Lista un Curso por ID", description = "Se debe enviar el ID del Curso a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CoursesResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @Operation(summary = "Lista de todos los curso dentro de la api ", description = "Se debe enviar la pagina y el tamaño de la pagina para recibir todos los cursos correspondientes")
    @GetMapping
    public ResponseEntity<Page<CoursesResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.getAll(page - 1, size));
    }

    @Operation(summary = "Edita la informacion de un curso por ID", description = "El ID a editar de debe enviar por URL Y la informacion a editar por body en formato Json")
    @PutMapping(path = "/{id}")
    public ResponseEntity<CoursesResponse> update(
            @Validated @RequestBody CoursesRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.service.update(id, request));
    }

    @Operation(summary = "Elimina un curso", description = "Se debe enviar el ID del curso a eliminar por URL")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
