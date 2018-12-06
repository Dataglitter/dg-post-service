package io.dataglitter.PostService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by reddys on 29/08/2018.
 */
@Configuration
public class FileStorageConfiguration {

    /**
     * Folder location for storing files
     */
    @Value("${file.uploadDir}")
    private String location;

    @Value("${file.profilePics}")
    private String profilePics;

    public String getLocation() {
        return location;
    }

    public String getProfilePics() {
        return profilePics;
    }
}
