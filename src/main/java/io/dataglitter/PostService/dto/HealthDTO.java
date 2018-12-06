package io.dataglitter.PostService.dto;

import io.dataglitter.PostService.constants.PostConstants;

/**
 * Created by reddys on 13/12/2017.
 */
public class HealthDTO {

    private String health;

    public HealthDTO() {
        this.health = PostConstants.HEALTHY;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
