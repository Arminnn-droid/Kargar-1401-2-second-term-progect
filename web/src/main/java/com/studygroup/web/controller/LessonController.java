package com.studygroup.web.controller;

import com.studygroup.web.dto.LessonDto;
import com.studygroup.web.models.Lesson;
import com.studygroup.web.models.UserEntity;
import com.studygroup.web.security.SecurityUtil;
import com.studygroup.web.service.GroupService;
import com.studygroup.web.service.LessonService;
import com.studygroup.web.service.UserService;
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
public class LessonController {
    private LessonService lessonService;
    private UserService userService;
    private GroupService groupService;

    @Autowired
    public LessonController(LessonService lessonService, UserService userService) {
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping("/lessons/{groupId}/new")
    public String createLessonForm(@PathVariable("groupId") Long groupId, Model model){
        Lesson lesson = new Lesson();
        model.addAttribute("groupId", groupId);
        model.addAttribute("lesson", lesson);
        return "lessons-create";
    }

    @PostMapping("/lessons/{groupId}")
    public String createLesson(@PathVariable("groupId") Long groupId, @ModelAttribute("lesson") LessonDto lessonDto, Model model){
        lessonService.createLesson(groupId, lessonDto);
        return "redirect:/groups/" + groupId;
    }

    @GetMapping("/lessons")
    public String lessonList(Model model){
        UserEntity user = new UserEntity();
        List<LessonDto> lessons = lessonService.findAllLessons();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("lessons", lessons);
        return "lessons-list";
    }

    @GetMapping("/lessons/{lessonId}/")
    public String viewLesson(@PathVariable("lessonId") Long lessonId, Model model){
        UserEntity user = new UserEntity();
        LessonDto lesson = lessonService.findByLessonId(lessonId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("group", lesson.getGroup());
        model.addAttribute("user", user);
        model.addAttribute("lesson", lesson);
        return "lesson-detail";
    }
    @GetMapping("/lessons/{lessonId}/edit")
    public String editLessonForm(@PathVariable("lessonId") Long lessonId, Model model){
        LessonDto lesson = lessonService.findByLessonId(lessonId);
        model.addAttribute("lesson", lesson);
        return "lessons-edit";
    }

    @PostMapping("/lessons/{lessonId}/edit")
    public String updateLesson(@PathVariable("lessonId") long lessonId,
                              @Valid @ModelAttribute("lesson") LessonDto lesson,
                              BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("lesson", lesson);
            return "lessons-edit";
        }
        LessonDto lessonDto = lessonService.findByLessonId(lessonId);
        lesson.setId(lessonId);
        lesson.setGroup(lessonDto.getGroup());
        lessonService.updateLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/{lessonId}/delete")
    public String deleteLesson(@PathVariable("lessonId") Long lessonId){
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons";
    }
}
