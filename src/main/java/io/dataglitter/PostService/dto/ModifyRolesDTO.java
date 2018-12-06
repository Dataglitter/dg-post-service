package io.dataglitter.PostService.dto;

import java.util.List;

/**
 * Created by reddys on 15/12/2017.
 */
public class ModifyRolesDTO {
    private String id;

    private List<String> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
