package com.riwi.courses.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.LessonsRequest;
import com.riwi.courses.api.dto.request.update.LessonUpdateRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;
import com.riwi.courses.api.dto.response.LessonsResponse;
import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.Lessons;
import com.riwi.courses.domain.repositories.CoursesRepository;
import com.riwi.courses.domain.repositories.LessonRepository;
import com.riwi.courses.infraestructure.abstract_services.ILessonsService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonsService implements ILessonsService{

    @Autowired
    private final LessonRepository lessonRepository;
    
    @Autowired
    private final CoursesRepository coursesRepository;
    
    @Override
    public LessonsResponse create(LessonsRequest request) {

        Courses course = this.coursesRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", request.getCourseId()));

        Lessons lesson = this.requestToEntity(request);
        lesson.setAssignments(new ArrayList<>());
        lesson.setCourseId(course);

        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonsResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<LessonsResponse> getAll(int page, int size) {

        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.lessonRepository.findAll(pagination)
            .map(this::entityToResponse);
    }

    @Override
    public LessonsResponse update(Long id, LessonUpdateRequest request) {

        Lessons lesson = this.find(id);
        lesson = this.requestUpdateToEntity(request);

        lesson.setLessonId(id);
        lesson.setLessonTitle(lesson.getLessonTitle());
        lesson.setContent(lesson.getContent());

        return this.entityToResponse(this.lessonRepository.save(lesson));
    }


    @Override
    public void delete(Long id) {
        this.lessonRepository.delete(this.find(id));
    }

    private Lessons requestToEntity(LessonsRequest request){

        Courses course = new Courses();
            BeanUtils.copyProperties(request.getCourseId(), course);

        return Lessons.builder()
            .lessonTitle(request.getLessonTitle())
            .content(request.getContent())
            .courseId(course)
            .build();
    }

    private Lessons requestUpdateToEntity(LessonUpdateRequest request){
        return Lessons.builder()
            .lessonTitle(request.getLessonTitle())
            .content(request.getContent())
            .build();
    }

    private LessonsResponse entityToResponse(Lessons lesson){

        CoursesResponse course = new CoursesResponse();
            BeanUtils.copyProperties(lesson.getCourseId(), course);

        return LessonsResponse.builder()
            .lessonId(lesson.getLessonId())
            .lessonTitle(lesson.getLessonTitle())
            .content(lesson.getContent())
            .courseId(course)
            .build();
    }

    private Lessons find(Long id){
        return this.lessonRepository.findById(id)
            .orElseThrow(()-> new IdNotFoundException("LESSON", id));
    }
    
}
