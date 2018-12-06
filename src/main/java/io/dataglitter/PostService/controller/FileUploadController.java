package io.dataglitter.PostService.controller;

import io.dataglitter.PostService.dto.FileUploadResponseDTO;
import io.dataglitter.PostService.service.FileSystemStorageService;
import io.dataglitter.PostService.repository.FileStorageRepository;
import io.dataglitter.PostService.service.S3StorageService;
import io.dataglitter.PostService.utils.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by reddys on 29/08/2018.
 */
@Controller
public class FileUploadController {

    private final FileStorageRepository fileStorageRepository;

    @Autowired
    public FileUploadController(S3StorageService storageService) {
        this.fileStorageRepository = storageService;
    }

    @PostMapping("/api/post/upload")
    public ResponseEntity<FileUploadResponseDTO> handlePostImageUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<FileUploadResponseDTO>(new FileUploadResponseDTO(fileStorageRepository.store(file, false)), HttpStatus.OK);
    }

    @PostMapping("/api/profile/upload")
    public ResponseEntity<FileUploadResponseDTO> handleProfilePicUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<FileUploadResponseDTO>(new FileUploadResponseDTO(fileStorageRepository.store(file, true)), HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}