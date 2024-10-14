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
        USERS.add(new User(UUID.randomUUID(), "johndoe", null));
        USERS.add(new User(UUID.randomUUID(), "janetjones", null));
        USERS.add(new User(UUID.randomUUID(), "markjunior", null));
    }

    public List<User> getUsers() {
        return USERS;
    }
}
