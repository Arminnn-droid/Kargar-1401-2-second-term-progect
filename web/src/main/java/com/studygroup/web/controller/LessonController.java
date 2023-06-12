package com.studygroup.web.controller;

import com.studygroup.web.dto.LessonDto;
import com.studygroup.web.models.Lesson;
import com.studygroup.web.service.LessonService;
import com.studygroup.web.service.impl.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LessonController {
    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
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
        return "redirect:/groups" + groupId;
    }
}
