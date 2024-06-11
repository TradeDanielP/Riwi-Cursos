package com.riwi.courses.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.courses.domain.entities.Assignments;
import com.riwi.courses.domain.entities.Submissions;
import com.riwi.courses.domain.entities.UsersEntity;

@Repository
public interface SubmissionRepository extends JpaRepository<Submissions, Long> {
    
    public Page<Submissions> findByAssignmentId(Assignments assignment, Pageable pageable);

    public Page<Submissions> findByUserId(UsersEntity user, Pageable pageable);

}
