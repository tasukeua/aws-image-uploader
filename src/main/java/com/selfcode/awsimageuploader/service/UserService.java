package com.selfcode.awsimageuploader.service;

import com.selfcode.awsimageuploader.model.User;
import com.selfcode.awsimageuploader.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.selfcode.awsimageuploader.bucket.BucketName.*;
import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FileStoreService fileStoreService;


    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @SneakyThrows
    public void uploadUserImage(UUID userId, MultipartFile file) {
        fileIsEmpty(file);

        fileIsImage(file);

        User user = getUserOrThrow(userId);

        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));

        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        fileStoreService.save(PROFILE_IMAGE.getBucketName(), user.getId() + "/" + filename, Optional.of(metaData), file.getInputStream());
        user.setUserImageLink(filename);
    }

    public byte[] downloadUserImage(UUID userId) {
        User user = getUserOrThrow(userId);
//        String path = String.format("%s/%s", PROFILE_IMAGE.getBucketName(), user.getId());
        String path = PROFILE_IMAGE.getBucketName();
        return user.getUserImageLink()
                .map(key -> fileStoreService.download(path, String.format("%s/%s", user.getId(), key)))
                .orElse(new byte[0]);
    }

    private User getUserOrThrow(UUID userId) {
        return userRepository.getUsers()
                .stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Cannot find %s user", userId)));
    }

    private static void fileIsImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType())
                .contains(file.getContentType()))
            throw new IllegalStateException("Cannot upload file, must be an image [" + file.getContentType() + "]");
    }

    private static void fileIsEmpty(MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload file [" + file.getSize() + "]");
    }
}
