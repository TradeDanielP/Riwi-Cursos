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
public class MessagesResponse {
    
    private Long messageId;
    private String messageContent;
    private Date sentDate;
    private UsersResponse senderId;
    private UsersResponse receiverId;
    private CoursesResponse courseId;

}
