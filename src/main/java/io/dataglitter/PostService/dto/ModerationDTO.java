package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 15/12/2017.
 */
public class ModerationDTO {

    private String id;

    private boolean moderated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }
}
