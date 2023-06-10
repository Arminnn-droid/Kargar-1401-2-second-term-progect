package com.studygroup.web.repository;

import com.studygroup.web.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByTitle(String url);
}
