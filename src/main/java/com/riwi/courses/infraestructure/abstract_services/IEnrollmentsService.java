package com.riwi.courses.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.courses.api.dto.request.create.EnrollmentRequest;
import com.riwi.courses.api.dto.response.EnrollmentResponse;
import com.riwi.courses.api.dto.response.UsersResponse;

public interface IEnrollmentsService extends
                CrudService<EnrollmentRequest, EnrollmentResponse, Long> {

        public Page<EnrollmentResponse> findByUserId(Long userId, int page, int size);

        public Page<UsersResponse> findByCourseId(Long courseId, int page, int size);

}