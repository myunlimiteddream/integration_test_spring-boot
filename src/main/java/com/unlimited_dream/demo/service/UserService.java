package com.unlimited_dream.demo.service;

import com.unlimited_dream.demo.dao.UserRepository;
import com.unlimited_dream.demo.dto.UserDTO;
import com.unlimited_dream.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        log.info("User service: create user");
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);
    }
}
