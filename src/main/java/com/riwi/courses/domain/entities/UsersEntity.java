package com.riwi.courses.domain.entities;

import java.util.List;

import com.riwi.courses.util.enums.ROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private ROLE role;

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    private List<Enrollments> enrollments;

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    private List<Submissions> submissions;

    @OneToMany(
            mappedBy = "instructorId",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    private List<Courses> courses;

    @OneToMany(
            mappedBy = "receiverId",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    private List<Messages> messagesByReceiver;

    @OneToMany(
            mappedBy = "senderId",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    private List<Messages> messagesBySender;
    
}
