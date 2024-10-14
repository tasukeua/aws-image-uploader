package com.selfcode.awsimageuploader.resource;

import com.selfcode.awsimageuploader.model.User;
import com.selfcode.awsimageuploader.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserResource {

    private final UserService userService;

    @GetMapping("all")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
