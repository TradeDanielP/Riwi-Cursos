package com.riwi.courses.api.dto.response;


import com.riwi.courses.util.enums.ROLE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private ROLE role;

}
