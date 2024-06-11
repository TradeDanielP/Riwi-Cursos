package com.riwi.courses.api.dto.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionsResponse {
    
    private Long submissionId;
    private String content;
    private Date submissionDate;
    private BigDecimal grade;
    private UsersResponse userId;
    private AssignmentsResponse assignmentId;

}
