package io.dataglitter.PostService.dto;

/**
 * Created by reddys on 15/12/2017.
 */
public class AddFollowerDTO {

    private String id;

    private String followerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}
