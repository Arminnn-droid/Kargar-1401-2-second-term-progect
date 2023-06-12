package com.studygroup.web.repository;

import com.studygroup.web.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByTitle(String url);
    @Query("SELECT c from Group c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Group> searchGroups(String query);
}
