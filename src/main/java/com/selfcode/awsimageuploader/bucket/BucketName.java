package com.selfcode.awsimageuploader.bucket;

import lombok.Getter;

@Getter
public enum BucketName {
    PROFILE_IMAGE("selfcode-image-uploader-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
