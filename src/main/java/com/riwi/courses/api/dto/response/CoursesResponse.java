package com.riwi.courses.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursesResponse {
    
    private Long courseId;
    private String courseName;
    private String description;
    private UsersResponse instructorId;

}
