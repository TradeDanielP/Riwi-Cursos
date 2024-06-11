package com.riwi.courses.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.courses.api.dto.request.create.SubmissionsRequest;
import com.riwi.courses.api.dto.request.update.SubmissionUpdateRequest;
import com.riwi.courses.api.dto.response.SubmissionsResponse;

public interface ISubmissionsService extends
                CrudService<SubmissionsRequest, SubmissionsResponse, Long>,
                UpdateService<SubmissionUpdateRequest, SubmissionsResponse, Long> {

        public Page<SubmissionsResponse> findByAssignmentId(Long assignmentId, int page, int size);

        public Page<SubmissionsResponse> findByUserId(Long userId, int page, int size);

}
