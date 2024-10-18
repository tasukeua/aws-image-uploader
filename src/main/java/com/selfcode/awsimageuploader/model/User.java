package com.selfcode.awsimageuploader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Setter
@AllArgsConstructor
public class User {

    @Getter
    private UUID id;

    @Getter
    private String username;

    private String userImageLink; // S3 key

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userImageLink, that.userImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userImageLink);
    }

}
