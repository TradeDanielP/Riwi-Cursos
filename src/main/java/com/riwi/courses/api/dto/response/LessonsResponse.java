package com.riwi.courses.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonsResponse {
    
    private Long lessonId;
    private String lessonTitle;
    private String content;
    private CoursesResponse courseId;

}
