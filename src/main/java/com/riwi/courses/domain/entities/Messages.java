package com.riwi.courses.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(length = 11, nullable = false)
    private int senderId;

    @Column(length = 11, nullable = false)
    private int receiverId;

    @Column(length = 11, nullable = false)
    private int courseId;

    @Lob
    @Column(nullable = false)
    private String messageContent;

    @Column(nullable = false)
    private LocalDate sentDate;

}
