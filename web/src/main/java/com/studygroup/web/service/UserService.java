package com.studygroup.web.service;

import com.studygroup.web.dto.RegistrationDto;
import com.studygroup.web.models.UserEntity;
import org.springframework.stereotype.Service;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
