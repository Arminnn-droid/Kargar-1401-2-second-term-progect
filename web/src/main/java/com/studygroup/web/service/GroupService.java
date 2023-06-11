package com.studygroup.web.service;

import java.util.List;
import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;

public interface GroupService {
    List<GroupDto> findAllGroups();
    Group saveGroup(Group group);

    GroupDto findGroupById(long groupId);

    void updateGroup(GroupDto group);
}
