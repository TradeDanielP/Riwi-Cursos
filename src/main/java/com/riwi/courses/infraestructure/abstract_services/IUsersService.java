package com.riwi.courses.infraestructure.abstract_services;

import com.riwi.courses.api.dto.request.create.UsersRequest;
import com.riwi.courses.api.dto.request.update.UserUpdateRequest;
import com.riwi.courses.api.dto.response.UsersResponse;

public interface IUsersService extends
        CrudService<UsersRequest, UsersResponse, Long>,
        UpdateService<UserUpdateRequest, UsersResponse, Long> {

}