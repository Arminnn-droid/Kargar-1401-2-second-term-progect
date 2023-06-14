package com.studygroup.web.mapper;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;

import java.util.stream.Collectors;

import static com.studygroup.web.mapper.LessonMapper.mapToLessonDto;

public class GroupMapper {

    public static Group mapToGroup(GroupDto group) {
        Group groupDto = Group.builder()
                .id(group.getId())
                .title(group.getTitle())
                .photoUrl(group.getPhotoUrl())
                .content(group.getContent())
                .createdOn(group.getCreatedOn())
                .updatedOn(group.getUpdatedOn())
                .build();
        return groupDto;
    }

    public static GroupDto mapToGroupDto(Group group){
        GroupDto groupDto = GroupDto.builder()
                .id(group.getId())
                .title(group.getTitle())
                .photoUrl(group.getPhotoUrl())
                .content(group.getContent())
                .createdOn(group.getCreatedOn())
                .updatedOn(group.getUpdatedOn())
                .lessons(group.getLessons().stream().map((lesson) -> mapToLessonDto(lesson)).collect(Collectors.toList()))
                .build();
        return groupDto;
    }
}
