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
public class EnrollmentResponse {
    
    private int enrollmentId;
    private int userId;
    private int courseId;
    private LocalDate enrollmenDate;

}
