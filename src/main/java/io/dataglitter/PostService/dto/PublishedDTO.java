package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 15/12/2017.
 */
public class PublishedDTO {

    private String id;

    private boolean published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
