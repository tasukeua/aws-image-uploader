package com.selfcode.awsimageuploader.repository;

import com.selfcode.awsimageuploader.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final InMemoryUserRepository inMemoryUserRepository;

    public List<User> getUsers() {
        return inMemoryUserRepository.getUsers();
    }

}
