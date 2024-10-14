package com.selfcode.awsimageuploader.service;

import com.selfcode.awsimageuploader.model.User;
import com.selfcode.awsimageuploader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
