package com.studygroup.web.service;

import java.util.List;
import com.studygroup.web.dto.GroupDto;

public interface GroupService {
    List<GroupDto> findAllGroups();
}
