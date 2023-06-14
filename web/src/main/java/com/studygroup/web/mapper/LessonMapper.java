package com.studygroup.web.mapper;

import com.studygroup.web.dto.LessonDto;
import com.studygroup.web.models.Lesson;

public class LessonMapper {
    public static Lesson mapToLesson(LessonDto lessonDto){
        return Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName())
                .lecture(lessonDto.getLecture())
                .startTime(lessonDto.getStartTime())
                .endTime(lessonDto.getEndTime())
                .createdOn(lessonDto.getCreatedOn())
                .updatedOn(lessonDto.getUpdatedOn())
                .photoUrl(lessonDto.getPhotoUrl())
                .build();
    }

    public static LessonDto mapToLessonDto(Lesson lesson){
        return LessonDto.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .lecture(lesson.getLecture())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .createdOn(lesson.getCreatedOn())
                .updatedOn(lesson.getUpdatedOn())
                .photoUrl(lesson.getPhotoUrl())
                .build();
    }
}
