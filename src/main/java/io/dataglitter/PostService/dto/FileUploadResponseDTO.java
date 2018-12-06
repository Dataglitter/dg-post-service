package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 29/08/2018.
 */
public class FileUploadResponseDTO {

    private String location;

    public FileUploadResponseDTO(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
