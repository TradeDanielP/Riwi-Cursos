package com.riwi.courses.infraestructure.abstract_services;

import com.riwi.courses.api.dto.request.create.CoursesRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;

public interface ICoursesService extends 
    CrudService<CoursesRequest, CoursesResponse, Long>,
    UpdateService<CoursesRequest, CoursesResponse, Long> {
    
}
