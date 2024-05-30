package com.riwi.courses.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionsResponse {
    
    private int submissionId;
    private String content;
    private LocalDate submissionDate;
    private double grade;
    private int userId;
    private int assignmentId;

}
