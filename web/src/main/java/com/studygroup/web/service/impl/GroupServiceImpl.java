package com.studygroup.web.service.impl;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.repository.GroupRepository;
import com.studygroup.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupDto> findAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map((group) -> mapToGroupDto(group)).collect(Collectors.toList());
    }

    private GroupDto mapToGroupDto(Group group){
        GroupDto groupDto = GroupDto.builder()
                .id(group.getId())
                .title(group.getTitle())
                .photoUrl(group.getPhotoUrl())
                .context(group.getContext())
                .createdOn(group.getCreatedOn())
                .updatedOn(group.getUpdatedOn())
                .build();
        return groupDto;
    }
}
