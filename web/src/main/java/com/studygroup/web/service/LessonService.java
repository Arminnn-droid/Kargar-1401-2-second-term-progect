package com.studygroup.web.service;

import com.studygroup.web.dto.LessonDto;

public interface LessonService {
    void createLesson(Long groupId, LessonDto lessonDto);
}
