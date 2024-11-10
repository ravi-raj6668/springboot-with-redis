package com.redis.springboot_redis.controller;

import com.redis.springboot_redis.entity.Real_User;
import com.redis.springboot_redis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/redis/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public final UserRepository userRepository;

    //create user
    @PostMapping("/createUser")
    public Real_User createUser(@RequestBody Real_User realUser) {
        LOG.info("Inside => create user() method");
        realUser.setUserId(UUID.randomUUID().toString());
        return userRepository.saveUser(realUser);
    }

    //find user
    @GetMapping("/getUser/{userId}")
    public Real_User getUser(@PathVariable("userId") String userId) {
        LOG.info("Inside => get single user() method");
        return userRepository.getUser(userId);
    }

    //find all user
    @GetMapping("/allUsers")
    public List<Real_User> getAllUser() {
        LOG.info("Inside => get all user() method");
        return userRepository.findAll();
    }

    //delete user
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        LOG.info("Inside => delete user() method");
        userRepository.deleteUser(userId);
    }
}
