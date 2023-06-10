package com.studygroup.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GroupDto {
    private Long id;
    private String title;
    private String photoUrl;
    private String context;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
