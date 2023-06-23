package com.studygroup.web.service.impl;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.models.UserEntity;
import com.studygroup.web.repository.GroupRepository;
import com.studygroup.web.repository.UserRepository;
import com.studygroup.web.security.SecurityUtil;
import com.studygroup.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.studygroup.web.mapper.GroupMapper.mapToGroup;
import static com.studygroup.web.mapper.GroupMapper.mapToGroupDto;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GroupDto> findAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map((group) -> mapToGroupDto(group)).collect(Collectors.toList());
    }

    @Override
    public Group saveGroup(GroupDto groupDto) {
        String username = SecurityUtil.getSessionUser().getUsername();
        UserEntity user = userRepository.findByUsername(username);
        Group group = mapToGroup(groupDto);
        group.setCreatedBy(user);
        return groupRepository.save(group);
    }

    @Override
    public GroupDto findGroupById(long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return mapToGroupDto(group);
    }

    @Override
    public void updateGroup(GroupDto groupDto) {
        String username = SecurityUtil.getSessionUser().getUsername();
        UserEntity user = userRepository.findByUsername(username);
        Group group = mapToGroup(groupDto);
        group.setCreatedBy(user);
        groupRepository.save(group);
    }

    @Override
    public void delete(long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public List<GroupDto> searchGroups(String query) {
        List<Group> groups = groupRepository.searchGroups(query);
        return groups.stream().map(group -> mapToGroupDto(group)).collect(Collectors.toList());
    }
}
