package com.studygroup.web.controller;

import com.studygroup.web.dto.GroupDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
                              BindingResult result){
        if (result.hasErrors()){
            return "groups-edit";
        }
        group.setId(groupId);
        groupService.updateGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}")
    public String groupDetail(@PathVariable("groupId") long groupId, Model model){
        GroupDto groupDto = groupService.findGroupById(groupId);
        model.addAttribute("group", groupDto);
        return "groups-detail";
    }

    @GetMapping("/groups/{groupId}/delete")
    public String deleteGroup(@PathVariable("groupId") long groupId){
        groupService.delete(groupId);
        return "redirect:/groups";
    }
}
