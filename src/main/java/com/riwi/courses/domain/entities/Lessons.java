package com.riwi.courses.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lessons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lessons {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String lessonId;

    @Column(length = 100,nullable = false)
    private String lessonTitle;

    @Lob
    private String content;

    @Column(length = 11 ,nullable = false)
    private int courseId;

}
