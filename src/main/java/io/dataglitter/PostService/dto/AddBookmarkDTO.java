package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 15/12/2017.
 */
public class AddBookmarkDTO {

    private String id;

    private String postId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
