package com.riwi.courses.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.courses.api.dto.request.create.AssignmentsRequest;
import com.riwi.courses.api.dto.request.update.AssignmentUpdateRequest;
import com.riwi.courses.api.dto.response.AssignmentsResponse;

public interface IAssignmentsService extends
        CrudService<AssignmentsRequest, AssignmentsResponse, Long>,
        UpdateService<AssignmentUpdateRequest, AssignmentsResponse, Long> {

    public Page<AssignmentsResponse> findByLessonId(Long lessonId, int page, int size);

}
