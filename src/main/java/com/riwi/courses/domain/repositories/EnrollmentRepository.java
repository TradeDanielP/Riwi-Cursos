package com.riwi.courses.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.Enrollments;
import com.riwi.courses.domain.entities.UsersEntity;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollments, Long> {

    public Page<Enrollments> findByUserId(UsersEntity user, Pageable pageable);
    
    public Page<Enrollments> findByCourseId(Courses course, Pageable pageable);
}
