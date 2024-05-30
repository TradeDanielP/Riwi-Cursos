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
public class MessagesResponse {
    
    private int messageId;
    private int senderId;
    private int receiverId;
    private int courseId;
    private String messageContent;
    private LocalDate sentDate;

}
