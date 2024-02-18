package com.aptit.octagnosis.cotroller;

import com.aptit.octagnosis.mapper.UserMapper;
import com.aptit.octagnosis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    Iterable<User> all(@RequestParam(required = false) String method, @RequestParam(required = false) String text) {
        Iterable<User> users = null;
        users = userMapper.getUsers(text);
        return users;
    }
}
