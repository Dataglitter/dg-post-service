package io.dataglitter.PostService.service;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.stream.Stream;

import io.dataglitter.PostService.config.FileStorageConfiguration;
import io.dataglitter.PostService.repository.FileStorageRepository;
import io.dataglitter.PostService.utils.StorageException;
import io.dataglitter.PostService.utils.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * Created by reddys on 29/08/2018.
 */
@Service
public class FileSystemStorageService implements FileStorageRepository {

    private final Path rootLocation;

    private final Path rootProfilePicsLocation;

    private Random rand = new Random();

    @Autowired
    public FileSystemStorageService(FileStorageConfiguration properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.rootProfilePicsLocation = Paths.get(properties.getProfilePics());
    }

    @Override
    public String store(MultipartFile file, Boolean profilePic) throws IOException {
        String filename = rand.nextInt(10000) + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            if(profilePic){
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, this.rootProfilePicsLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, this.rootLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return filename;
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }


    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
