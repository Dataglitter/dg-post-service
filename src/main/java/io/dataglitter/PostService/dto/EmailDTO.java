package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 15/12/2017.
 */
public class EmailDTO {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
