package com.riwi.courses.api.dto.request.create;

import com.riwi.courses.api.dto.request.update.UserUpdateRequest;
import com.riwi.courses.util.enums.ROLE;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest extends UserUpdateRequest {

    @NotNull(message = "el Rol no debe ser nulo")
    private ROLE role;

}
