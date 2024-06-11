package com.riwi.courses.api.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentsResponse {
    
    private Long assignmentId;
    private String assignmentTitle;
    private String description;
    private Date dueDate;
    private LessonsResponse lessonId;

}
