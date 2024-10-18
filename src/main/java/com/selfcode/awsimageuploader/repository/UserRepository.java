package com.selfcode.awsimageuploader.repository;

import com.selfcode.awsimageuploader.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final InMemoryUserRepository inMemoryUserRepository;

    public List<User> getUsers() {
        return inMemoryUserRepository.getUsers();
    }

    public boolean getUser(UUID userId) {
        return inMemoryUserRepository.getUser(userId);
    }
}
