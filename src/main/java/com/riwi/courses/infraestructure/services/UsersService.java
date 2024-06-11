package com.riwi.courses.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.courses.api.dto.request.create.UsersRequest;
import com.riwi.courses.api.dto.request.update.UserUpdateRequest;
import com.riwi.courses.api.dto.response.UsersResponse;
import com.riwi.courses.domain.entities.UsersEntity;
import com.riwi.courses.domain.repositories.UsersRepository;
import com.riwi.courses.infraestructure.abstract_services.IUsersService;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersService implements IUsersService{

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public UsersResponse create(UsersRequest request) {
        UsersEntity users = this.requestToEntity(request);
        users.setCourses(new ArrayList<>());
        users.setEnrollments(new ArrayList<>());
        users.setMessagesByReceiver(new ArrayList<>());
        users.setMessagesBySender(new ArrayList<>());
        users.setSubmissions(new ArrayList<>());
        return this.entityToResponse(this.usersRepository.save(users));
    }

    @Override
    public Page<UsersResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public UsersResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public UsersResponse update(Long id, UserUpdateRequest request) {
        UsersEntity user = this.find(id);
        
        UsersEntity userUpdate = this.requestUpdateToEntity(request);
        userUpdate.setUserId(id);
        userUpdate.setRole(user.getRole());
        userUpdate.setCourses(user.getCourses());
        userUpdate.setEnrollments(user.getEnrollments());
        userUpdate.setMessagesByReceiver(user.getMessagesByReceiver());
        userUpdate.setMessagesBySender(user.getMessagesBySender());
        userUpdate.setSubmissions(user.getSubmissions());

        return this.entityToResponse(this.usersRepository.save(userUpdate));

    }

    @Override
    public void delete(Long id) {
        UsersEntity user = this.find(id);
        this.usersRepository.delete(user);
    }

    private UsersResponse entityToResponse(UsersEntity user) {
        return UsersResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();

    }

    private UsersEntity requestToEntity(UsersRequest user) {
        return UsersEntity.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }

    private UsersEntity requestUpdateToEntity(UserUpdateRequest user) {
        return UsersEntity.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    private UsersEntity find(Long id) {
        return this.usersRepository.findById(id)
            .orElseThrow(() -> new IdNotFoundException("USER", id));
    }
    
}
