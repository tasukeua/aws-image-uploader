package com.selfcode.awsimageuploader.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileStoreService {

    private final AmazonS3 s3;

    public void save(String path,
                     String filename,
                     Optional<Map<String, String>> optionalMetaData,
                     InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            s3.putObject(path, filename, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to AWS S3 Bucket", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object s3Object = s3.getObject(path, key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Filed to download", e);
        }
    }
}
