package com.riwi.courses.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.courses.domain.entities.Lessons;

@Repository
public interface LessonRepository extends JpaRepository<Lessons, Long>{
    
}
