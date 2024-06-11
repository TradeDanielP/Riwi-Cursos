package com.riwi.courses.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.courses.domain.entities.Assignments;
import com.riwi.courses.domain.entities.Lessons;

@Repository
public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
    
    public Page<Assignments> findByLessonId(Lessons lesson, Pageable pageable);

}
