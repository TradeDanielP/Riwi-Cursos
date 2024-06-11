package com.riwi.courses.infraestructure.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.EnrollmentRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;
import com.riwi.courses.api.dto.response.EnrollmentResponse;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.Enrollments;
import com.riwi.courses.domain.entities.UsersEntity;
import com.riwi.courses.domain.repositories.CoursesRepository;
import com.riwi.courses.domain.repositories.EnrollmentRepository;
import com.riwi.courses.domain.repositories.UsersRepository;
import com.riwi.courses.infraestructure.abstract_services.IEnrollmentsService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollmentsService implements IEnrollmentsService {

    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {

        UsersEntity user = this.usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new IdNotFoundException("STUDENT", request.getUserId()));

        Courses course = this.coursesRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", request.getCourseId()));

        Enrollments enrollment = this.requestToEntity(request);

        enrollment.setUserId(user);
        enrollment.setCourseId(course);
        enrollment.setEnrollmentDate(new Date());

        return this.entityToResponse(this.enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public void delete(Long id) {
        this.enrollmentRepository.delete(this.find(id));
    }

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Page<EnrollmentResponse> findByUserId(Long userId, int page, int size) {
        UsersEntity user = this.usersRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("USER", userId));

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Enrollments> enrollmentsPage = this.enrollmentRepository.findByUserId(user, pageable);

        return enrollmentsPage.map(this::entityToResponse);
    }

    @Override
    public Page<UsersResponse> findByCourseId(Long courseId, int page, int size) {
        Courses course = this.coursesRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("COURSE", courseId));

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Enrollments> enrollmentsPage = this.enrollmentRepository.findByCourseId(course, pageable);

        List<UsersResponse> usersResponses = enrollmentsPage.stream()
                .map(enrollment -> {
                    UsersResponse userResponse = new UsersResponse();
                    BeanUtils.copyProperties(enrollment.getUserId(), userResponse);
                    return userResponse;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(usersResponses, pageable, enrollmentsPage.getTotalElements());
    }


    private Enrollments requestToEntity(EnrollmentRequest request) {

        UsersEntity user = new UsersEntity();
        BeanUtils.copyProperties(request.getUserId(), user);

        Courses course = new Courses();
        BeanUtils.copyProperties(request.getCourseId(), course);

        return Enrollments.builder()
                .courseId(course)
                .userId(user)
                .build();
    }

    private EnrollmentResponse entityToResponse(Enrollments enrollment) {

        UsersResponse user = new UsersResponse();
        BeanUtils.copyProperties(enrollment.getUserId(), user);

        CoursesResponse course = new CoursesResponse();
        BeanUtils.copyProperties(enrollment.getCourseId(), course);

        return EnrollmentResponse.builder()
                .enrollmentId(enrollment.getEnrollment_id())
                .enrollmenDate(enrollment.getEnrollmentDate())
                .userId(user)
                .courseId(course)
                .build();
    }

    private Enrollments find(Long id) {
        return this.enrollmentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("ENROLLMENT", id));
    }

}
