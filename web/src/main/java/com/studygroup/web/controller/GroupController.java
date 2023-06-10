package com.studygroup.web.controller;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String listGroups(Model model){
        List<GroupDto> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups-list";
    }

    @GetMapping("/groups/new")
    public String createGroupForm(Model model){
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups-create";
    }
}
