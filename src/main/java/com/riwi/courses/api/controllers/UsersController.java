package com.riwi.courses.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.courses.api.dto.request.create.UsersRequest;
import com.riwi.courses.api.dto.request.update.UserUpdateRequest;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.infraestructure.abstract_services.IUsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UsersController {

    @Autowired
    private final IUsersService usersService;

    @Operation(summary = "Crea un Usuario", description = "Crea un Usuario se debe enviar la informacion en formato JSON")
    @PostMapping
    public ResponseEntity<UsersResponse> create(
            @Validated @RequestBody UsersRequest usersRequest) {
        return ResponseEntity.ok(this.usersService.create(usersRequest));
    }

    @Operation(summary = "Lista un Usuario por ID", description = "Se debe enviar el ID del usuario a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UsersResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.usersService.getById(id));
    }

    @Operation(summary = "Edita la informacion de un usuario por ID", description = "El ID del usuario a editar de debe enviar por URL Y la informacion a editar por body en formato Json")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UsersResponse> update(
            @Validated @RequestBody UserUpdateRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.usersService.update(id, request));
    }

    @Operation(summary = "Elimina un Usuario", description = "Se debe enviar el ID del Usuario a eliminar por URL")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
