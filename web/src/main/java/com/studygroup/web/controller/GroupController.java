package com.studygroup.web.controller;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.models.UserEntity;
import com.studygroup.web.security.SecurityUtil;
import com.studygroup.web.service.GroupService;
import com.studygroup.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupController {
    private GroupService groupService;
    private UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String listGroups(Model model){
        UserEntity user = new UserEntity();
        List<GroupDto> groups = groupService.findAllGroups();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("groups", groups);
        return "groups-list";
    }

    @GetMapping("/groups/new")
    public String createGroupForm(Model model){
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups-create";
    }

    @PostMapping("/groups/new")
    public String saveGroup(@Valid @ModelAttribute("group") GroupDto groupDto, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("group", groupDto);
            return "groups-create";
        }
        groupService.saveGroup(groupDto);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}/edit")
    public String editGroupForm(@PathVariable("groupId") long groupId, Model model){
        GroupDto group = groupService.findGroupById(groupId);
        model.addAttribute("group", group);
        return "groups-edit";
    }

    @PostMapping("/groups/{groupId}/edit")
    public String updateGroup(@PathVariable("groupId") long groupId,
                              @Valid @ModelAttribute("group") GroupDto group,
                              BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("group", group);
            return "groups-edit";
        }
        group.setId(groupId);
        groupService.updateGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}")
    public String groupDetail(@PathVariable("groupId") long groupId, Model model){
        UserEntity user = new UserEntity();
        GroupDto groupDto = groupService.findGroupById(groupId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("group", groupDto);
        return "groups-detail";
    }

    @GetMapping("/groups/{groupId}/delete")
    public String deleteGroup(@PathVariable("groupId") long groupId){
        groupService.delete(groupId);
        return "redirect:/groups";
    }

    @GetMapping("/groups/search")
    public String searchGroup(@RequestParam(value = "query")String query, Model model){
        List<GroupDto> groups = groupService.searchGroups(query);
        model.addAttribute("groups", groups);
        return "groups-list";
    }
}
