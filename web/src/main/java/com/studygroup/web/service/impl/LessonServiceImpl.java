package com.studygroup.web.service.impl;

import com.studygroup.web.dto.LessonDto;
import com.studygroup.web.models.Group;
import com.studygroup.web.models.Lesson;
import com.studygroup.web.repository.GroupRepository;
import com.studygroup.web.repository.LessonRepository;
import com.studygroup.web.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.studygroup.web.mapper.GroupMapper.mapToGroup;
import static com.studygroup.web.mapper.LessonMapper.mapToLesson;
import static com.studygroup.web.mapper.LessonMapper.mapToLessonDto;

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

    @Override
    public List<LessonDto> findAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map((lesson) -> mapToLessonDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public LessonDto findByLessonId(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return mapToLessonDto(lesson);
    }

    @Override
    public void updateLesson(LessonDto lessonDto) {
        Lesson lesson = mapToLesson(lessonDto);
        lessonRepository.save(lesson);
    }
}
