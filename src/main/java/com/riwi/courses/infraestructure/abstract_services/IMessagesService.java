package com.riwi.courses.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.courses.api.dto.request.create.MessagesRequest;
import com.riwi.courses.api.dto.response.MessagesResponse;

public interface IMessagesService extends
        CrudService<MessagesRequest, MessagesResponse, Long> {

    public Page<MessagesResponse> findByCourseId(Long courseId, int page, int size);

    public Page<MessagesResponse> findBySenderAndReceiverId(Long senderId, Long receiverId, int page, int size);

}
