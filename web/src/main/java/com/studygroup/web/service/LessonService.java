package com.studygroup.web.service;

import com.studygroup.web.dto.LessonDto;

import java.util.List;

public interface LessonService {
    void createLesson(Long groupId, LessonDto lessonDto);

    List<LessonDto> findAllLessons();

    LessonDto findByLessonId(Long lessonId);

    void updateLesson(LessonDto lessonDto);
}
