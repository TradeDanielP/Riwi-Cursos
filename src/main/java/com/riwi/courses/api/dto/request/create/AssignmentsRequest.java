package com.riwi.courses.api.dto.request.create;


import com.riwi.courses.api.dto.request.update.AssignmentUpdateRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentsRequest extends AssignmentUpdateRequest {

    @NotNull(message = "Lesson id is required")
    @Min(value = 1, message = "Lesson id must be greater than 0")
    private Long lessonId;

}