package com.riwi.courses.infraestructure.services;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.MessagesRequest;
import com.riwi.courses.api.dto.response.CoursesResponse;
import com.riwi.courses.api.dto.response.MessagesResponse;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.domain.entities.Courses;
import com.riwi.courses.domain.entities.Messages;
import com.riwi.courses.domain.entities.UsersEntity;
import com.riwi.courses.domain.repositories.CoursesRepository;
import com.riwi.courses.domain.repositories.MessageRepository;
import com.riwi.courses.domain.repositories.UsersRepository;
import com.riwi.courses.infraestructure.abstract_services.IMessagesService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessagesService implements IMessagesService {

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public MessagesResponse create(MessagesRequest request) {

        UsersEntity sender = this.usersRepository.findById(request.getSenderId())
                .orElseThrow(() -> new IdNotFoundException("SENDER", request.getSenderId()));

        UsersEntity receiver = this.usersRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new IdNotFoundException("RECEIVER", request.getReceiverId()));

        Courses course = this.coursesRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IdNotFoundException("COURSE", request.getCourseId()));

        Messages messages = this.requestToEntity(request);

        messages.setSenderId(sender);
        messages.setReceiverId(receiver);
        messages.setCourseId(course);
        messages.setSentDate(new Date());

        return this.entityToResponse(this.messageRepository.save(messages));
    }

    @Override
    public MessagesResponse getById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<MessagesResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Page<MessagesResponse> findByCourseId(Long courseId, int page, int size) {
        Courses course = this.coursesRepository.findById(courseId)
                .orElseThrow(() -> new IdNotFoundException("COURSE", courseId));

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Messages> messagesPage = this.messageRepository.findByCourseId(course, pageable);

        return messagesPage.map(this::entityToResponse);
    }

    @Override
    public Page<MessagesResponse> findBySenderAndReceiverId(Long senderId, Long receiverId, int page, int size) {
        UsersEntity sender = this.usersRepository.findById(senderId)
                .orElseThrow(() -> new IdNotFoundException("SENDER", senderId));

        UsersEntity receiver = this.usersRepository.findById(receiverId)
                .orElseThrow(() -> new IdNotFoundException("RECEIVER", receiverId));

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Messages> messagesPage = this.messageRepository.findBySenderIdAndReceiverId(sender, receiver, pageable);

        return messagesPage.map(this::entityToResponse);
    }

    private Messages requestToEntity(MessagesRequest request) {

        UsersEntity sender = new UsersEntity();
        BeanUtils.copyProperties(request.getSenderId(), sender);

        UsersEntity receiver = new UsersEntity();
        BeanUtils.copyProperties(request.getReceiverId(), receiver);

        Courses course = new Courses();
        BeanUtils.copyProperties(request.getCourseId(), course);

        return Messages.builder()
                .senderId(sender)
                .receiverId(receiver)
                .courseId(course)
                .messageContent(request.getMessageContent())
                .build();
    }

    private MessagesResponse entityToResponse(Messages message) {

        UsersResponse sender = new UsersResponse();
        BeanUtils.copyProperties(message.getSenderId(), sender);

        UsersResponse receiver = new UsersResponse();
        BeanUtils.copyProperties(message.getReceiverId(), receiver);

        CoursesResponse course = new CoursesResponse();
        BeanUtils.copyProperties(message.getCourseId(), course);

        return MessagesResponse.builder()
                .courseId(course)
                .messageContent(message.getMessageContent())
                .messageId(message.getMessageId())
                .receiverId(receiver)
                .senderId(sender)
                .sentDate(message.getSentDate())
                .build();
    }
}
