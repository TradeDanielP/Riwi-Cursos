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

import com.riwi.courses.api.dto.request.create.SubmissionsRequest;
import com.riwi.courses.api.dto.request.update.SubmissionUpdateRequest;
import com.riwi.courses.api.dto.response.AssignmentsResponse;
import com.riwi.courses.api.dto.response.SubmissionsResponse;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.domain.entities.Assignments;
import com.riwi.courses.domain.entities.Submissions;
import com.riwi.courses.domain.entities.UsersEntity;
import com.riwi.courses.domain.repositories.AssignmentsRepository;
import com.riwi.courses.domain.repositories.SubmissionRepository;
import com.riwi.courses.domain.repositories.UsersRepository;
import com.riwi.courses.infraestructure.abstract_services.ISubmissionsService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionsService implements ISubmissionsService {

    @Autowired
    private final SubmissionRepository submissionRepository;

    @Autowired
    private final AssignmentsRepository assignmentsRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public SubmissionsResponse create(SubmissionsRequest request) {

        Assignments assignment = this.assignmentsRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new IdNotFoundException("ASSIGNMENT", request.getAssignmentId()));

        UsersEntity user = this.usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new IdNotFoundException("USER", request.getUserId()));

        Submissions submission = this.requestToEntity(request);
        submission.setSubmissionDate(new Date());
        submission.setAssignmentId(assignment);
        submission.setUserId(user);

        return this.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public SubmissionsResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public void delete(Long id) {
        this.submissionRepository.delete(this.find(id));
    }

    @Override
    public Page<SubmissionsResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SubmissionsResponse update(Long id, SubmissionUpdateRequest request) {

        Submissions submission = this.find(id);
        submission = this.requestUpdateToEntity(request);

        submission.setSubmissionId(id);
        submission.setContent(request.getContent());
        submission.setGrade(request.getGrade());

        return this.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public Page<SubmissionsResponse> findByAssignmentId(Long assignmentId, int page, int size) {
        Assignments assignment = this.assignmentsRepository.findById(assignmentId)
                .orElseThrow(() -> new IdNotFoundException("ASSIGNMENT", assignmentId));
    
        Pageable pageable = PageRequest.of(page - 1, size);
    
        Page<Submissions> submissionsPage = this.submissionRepository.findByAssignmentId(assignment, pageable);
    
        List<SubmissionsResponse> submissionsResponses = submissionsPage.getContent().stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    
        return new PageImpl<>(submissionsResponses, pageable, submissionsPage.getTotalElements());
    }
    

    @Override
    public Page<SubmissionsResponse> findByUserId(Long userId, int page, int size) {
        UsersEntity user = this.usersRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("USER", userId));

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Submissions> submissionsPage = this.submissionRepository.findByUserId(user, pageable);

        return submissionsPage.map(this::entityToResponse);
    }

    private Submissions requestToEntity(SubmissionsRequest request) {

        Assignments assignment = new Assignments();
        BeanUtils.copyProperties(request.getAssignmentId(), assignment);

        UsersEntity user = new UsersEntity();
        BeanUtils.copyProperties(request.getUserId(), user);

        return Submissions.builder()
                .content(request.getContent())
                .grade(request.getGrade())
                .assignmentId(assignment)
                .userId(user)
                .build();
    }

    private Submissions requestUpdateToEntity(SubmissionUpdateRequest request) {
        return Submissions.builder()
                .content(request.getContent())
                .grade(request.getGrade())
                .submissionDate(new Date())
                .build();
    }

    private SubmissionsResponse entityToResponse(Submissions submission) {

        AssignmentsResponse assignment = new AssignmentsResponse();
        BeanUtils.copyProperties(submission.getAssignmentId(), assignment);

        UsersResponse user = new UsersResponse();
        BeanUtils.copyProperties(submission.getUserId(), user);

        return SubmissionsResponse.builder()
                .submissionId(submission.getSubmissionId())
                .content(submission.getContent())
                .grade(submission.getGrade())
                .submissionDate(submission.getSubmissionDate())
                .assignmentId(assignment)
                .userId(user)
                .build();
    }

    private Submissions find(Long id) {
        return this.submissionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("SUBMISSION", id));
    }

}
