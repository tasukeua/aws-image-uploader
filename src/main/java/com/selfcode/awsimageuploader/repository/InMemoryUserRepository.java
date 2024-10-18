package com.selfcode.awsimageuploader.repository;

import com.selfcode.awsimageuploader.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryUserRepository {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User(UUID.fromString("24640c44-77e9-4f67-ae02-c8cf3f28a8d7"), "johndoe", null));
        USERS.add(new User(UUID.fromString("60f5f39f-97fb-4ecb-b9bb-42a5587d948b"), "janetjones", null));
        USERS.add(new User(UUID.fromString("d68a897c-57bb-4226-8e4f-9e6fb13588ea"), "markjunior", null));
    }

    public List<User> getUsers() {
        return USERS;
    }

    public boolean getUser(UUID userId) {
        return USERS.stream().anyMatch(user -> user.getId().equals(userId));
    }
}
