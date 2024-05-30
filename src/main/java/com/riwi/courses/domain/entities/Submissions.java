package com.riwi.courses.domain.entities;

import java.time.LocalDate;

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

@Entity(name = "submission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Submissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int submissionId;

    @Lob
    private String content;

    @Column(nullable = false)
    private LocalDate submissionDate;

    @Column(length = 5)
    private double grade;

    @Column(length = 11, nullable = false)
    private int userId;

    @Column(length = 11, nullable = false)
    private int assignmentId;

}
