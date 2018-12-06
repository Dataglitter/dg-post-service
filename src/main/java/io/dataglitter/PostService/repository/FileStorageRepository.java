package io.dataglitter.PostService.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by reddys on 29/08/2018.
 */
public interface FileStorageRepository {

    String store(MultipartFile file, Boolean profilePic) throws IOException;

}