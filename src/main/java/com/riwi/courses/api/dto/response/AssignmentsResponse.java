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
public class AssignmentsResponse {
    
    private int assignmentId;
    private String assignmentTitle;
    private String description;
    private LocalDate dueDate;
    private int lessonId;

}
