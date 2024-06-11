package com.riwi.courses.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.Messages;
import com.riwi.courses.domain.entities.UsersEntity;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
    
    public Page<Messages> findByCourseId(Courses course, Pageable pageable);

    public Page<Messages> findBySenderIdAndReceiverId(UsersEntity sender, UsersEntity receiver, Pageable pageable);

}
