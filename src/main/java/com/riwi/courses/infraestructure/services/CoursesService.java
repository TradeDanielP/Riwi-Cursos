package com.riwi.courses.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.CoursesRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.UsersEntity;
import com.riwi.courses.domain.repositories.CoursesRepository;
import com.riwi.courses.domain.repositories.UsersRepository;
import com.riwi.courses.infraestructure.abstract_services.ICoursesService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CoursesService implements ICoursesService {

    @Autowired
    private final CoursesRepository coursesRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public CoursesResponse create(CoursesRequest request) {

        UsersEntity user = this.usersRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new IdNotFoundException("INSTRUCTOR", request.getInstructorId()));

        Courses course = this.requestToEntity(request);
        course.setEnrollments(new ArrayList<>());
        course.setLessons(new ArrayList<>());
        course.setMessages(new ArrayList<>());
        course.setInstructorId(user);

        return this.entityToResponse(this.coursesRepository.save(course));
    }

    @Override
    public CoursesResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<CoursesResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.coursesRepository.findAll(pagination)
            .map(this::entityToResponse);
    }

    @Override
    public CoursesResponse update(Long id, CoursesRequest request) {

        Courses course = this.find(id);

        UsersEntity instructor = this.usersRepository.findById(request.getInstructorId())
            .orElseThrow(()-> new IdNotFoundException("INSTRUCTOR", id));

        course = this.requestToEntity(request);

        course.setCourseId(id);
        course.setInstructorId(instructor);
        course.setEnrollments(course.getEnrollments());
        course.setLessons(course.getLessons());
        course.setMessages(course.getMessages());

        return this.entityToResponse(this.coursesRepository.save(course));
    }


    @Override
    public void delete(Long id) {
        this.coursesRepository.delete(this.find(id));
    }

    private Courses requestToEntity(CoursesRequest request){
        UsersEntity user = new UsersEntity();
            BeanUtils.copyProperties(request.getInstructorId(), user);

        return Courses.builder()
            .courseName(request.getCourseName())
            .description(request.getDescription())
            .instructorId(user)
            .build();
    }

    private CoursesResponse entityToResponse(Courses course){

        UsersResponse instructor = new UsersResponse();
            BeanUtils.copyProperties(course.getInstructorId(), instructor);

        return CoursesResponse.builder()
            .courseId(course.getCourseId())
            .courseName(course.getCourseName())
            .description(course.getDescription())
            .instructorId(instructor)
            .build();
    }

    private Courses find(Long id){
        return this.coursesRepository.findById(id)
            .orElseThrow(()-> new IdNotFoundException("COURSE", id));
    }
}
