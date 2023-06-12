package com.studygroup.web.service.impl;

import com.studygroup.web.dto.LessonDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.models.Lesson;
import com.studygroup.web.repository.GroupRepository;
import com.studygroup.web.repository.LessonRepository;
import com.studygroup.web.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepository;
    private GroupRepository groupRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, GroupRepository groupRepository) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void createLesson(Long groupId, LessonDto lessonDto) {
        Group group = groupRepository.findById(groupId).get();
        Lesson lesson = mapToLesson(lessonDto);
        lesson.setGroup(group);
        lessonRepository.save(lesson);
    }

    private Lesson mapToLesson(LessonDto lessonDto){
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
}
