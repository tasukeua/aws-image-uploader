package com.selfcode.awsimageuploader.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.rmi.server.UID;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {

    private UID id;
    private String username;
    private String userImageLink; // S3 key
}
