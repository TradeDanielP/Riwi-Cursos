package com.riwi.courses.infraestructure.abstract_services;

import com.riwi.courses.api.dto.request.create.LessonsRequest;
import com.riwi.courses.api.dto.request.update.LessonUpdateRequest;
import com.riwi.courses.api.dto.response.LessonsResponse;

public interface ILessonsService extends 
    CrudService<LessonsRequest, LessonsResponse, Long>,
    UpdateService<LessonUpdateRequest, LessonsResponse, Long>{
    
}

