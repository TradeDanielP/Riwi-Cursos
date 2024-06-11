package com.riwi.courses.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.AssignmentsRequest;
import com.riwi.courses.api.dto.request.update.AssignmentUpdateRequest;
import com.riwi.courses.api.dto.response.AssignmentsResponse;
import com.riwi.courses.api.dto.response.LessonsResponse;
import com.riwi.courses.domain.entities.Assignments;
import com.riwi.courses.domain.entities.Lessons;
import com.riwi.courses.domain.repositories.AssignmentsRepository;
import com.riwi.courses.domain.repositories.LessonRepository;
import com.riwi.courses.infraestructure.abstract_services.IAssignmentsService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignmentsService implements IAssignmentsService {

    @Autowired
    private final AssignmentsRepository assignmentsRepository;

    @Autowired
    private final LessonRepository lessonRepository;

    @Override
    public AssignmentsResponse create(AssignmentsRequest request) {

        Lessons lesson = this.lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new IdNotFoundException("LESSON", request.getLessonId()));

        Assignments assignment = this.requestToEntity(request);
        assignment.setSubmissions(new ArrayList<>());
        assignment.setLessonId(lesson);

        return this.entityToResponse(this.assignmentsRepository.save(assignment));
    }

    @Override
    public AssignmentsResponse getById(Long id) {
        return this.entityToResponse(this.find(id));

    }

    @Override
    public Page<AssignmentsResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AssignmentsResponse update(Long id, AssignmentUpdateRequest request) {
        Assignments assignment = this.find(id);
        assignment = this.requestUpdateToEntity(request);

        assignment.setId(id);
        assignment.setAssignmentTitle(request.getAssignmentTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDueDate(request.getDueDate());

        return this.entityToResponse(this.assignmentsRepository.save(assignment));
    }

    @Override
    public void delete(Long id) {
        this.assignmentsRepository.delete(this.find(id));
    }

    @Override
    public Page<AssignmentsResponse> findByLessonId(Long lessonId, int page, int size) {
        Lessons lesson = this.lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IdNotFoundException("LESSON", lessonId));

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Assignments> assignmentsPage = this.assignmentsRepository.findByLessonId(lesson, pageable);

        return assignmentsPage.map(this::entityToResponse);
    }

    private Assignments requestToEntity(AssignmentsRequest request) {

        Lessons lesson = new Lessons();
        BeanUtils.copyProperties(request.getLessonId(), lesson);

        return Assignments.builder()
                .assignmentTitle(request.getAssignmentTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .lessonId(lesson)
                .build();
    }

    private Assignments requestUpdateToEntity(AssignmentUpdateRequest request) {
        return Assignments.builder()
                .assignmentTitle(request.getAssignmentTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .build();
    }

    private AssignmentsResponse entityToResponse(Assignments assignment) {

        LessonsResponse lesson = new LessonsResponse();
        BeanUtils.copyProperties(assignment.getLessonId(), lesson);

        return AssignmentsResponse.builder()
                .assignmentId(assignment.getId())
                .assignmentTitle(assignment.getAssignmentTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .lessonId(lesson)
                .build();
    }

    private Assignments find(Long id) {
        return this.assignmentsRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("ASSIGNMENT", id));
    }

}
