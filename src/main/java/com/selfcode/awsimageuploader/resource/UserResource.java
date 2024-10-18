package com.selfcode.awsimageuploader.resource;

import com.selfcode.awsimageuploader.model.User;
import com.selfcode.awsimageuploader.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserResource {

    private final UserService userService;

    @GetMapping("all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadUserImage(@PathVariable("userId") UUID userId,
                                @RequestParam("file") MultipartFile file) {
        userService.uploadUserImage(userId, file);
    }

    @GetMapping("{userId}/image/download")
    public ResponseEntity<byte[]> downloadUserImage(@PathVariable("userId") UUID userId) {
        byte[] imageData = userService.downloadUserImage(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

}
