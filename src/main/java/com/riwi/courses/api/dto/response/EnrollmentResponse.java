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
public class EnrollmentResponse {
    
    private Long enrollmentId;
    private UsersResponse userId;
    private CoursesResponse courseId;
    private Date enrollmenDate;

}
