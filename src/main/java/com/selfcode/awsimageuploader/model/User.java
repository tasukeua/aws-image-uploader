package com.selfcode.awsimageuploader.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Getter
    private UUID id;

    @Getter
    private String username;

    private String userImageLink; // S3 key

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

}
