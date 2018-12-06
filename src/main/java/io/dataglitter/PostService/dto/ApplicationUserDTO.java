package io.dataglitter.PostService.dto;

import io.dataglitter.PostService.document.ApplicationUser;
import io.dataglitter.PostService.utils.Utilities;

import java.util.Date;
import java.util.List;

/**
 * Created by reddys on 14/12/2017.
 */
public class ApplicationUserDTO {

    private String id;

    private String email;

    private String name;

    private String designation;

    private List<String> followers;

    private List<String> roles;

    private List<String> bookmarks;

    private String about;

    private String facebookURL;

    private String twitterURL;

    private String instagramURL;

    private String linkedinURL;

    private String githubURL;

    private Date registeredOn = new Date();

    private String profilePicture;

    private String accountStatus;

    private boolean active;

    public ApplicationUserDTO(){

    }

    public ApplicationUserDTO(ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.email = applicationUser.getEmail().toLowerCase();
        this.name = applicationUser.getName();
        this.designation = applicationUser.getDesignation();
        this.followers = applicationUser.getFollowers();
        this.roles = applicationUser.getRoles();
        this.bookmarks = applicationUser.getBookmarks();
        this.about = applicationUser.getAbout();
        this.facebookURL = applicationUser.getFacebookURL();
        this.twitterURL = applicationUser.getTwitterURL();
        this.instagramURL = applicationUser.getInstagramURL();
        this.linkedinURL = applicationUser.getLinkedinURL();
        this.githubURL = applicationUser.getGithubURL();
        this.registeredOn = applicationUser.getRegisteredOn();
        this.profilePicture = applicationUser.getProfilePicture();
        this.accountStatus = applicationUser.getAccountStatus();
        this.active = applicationUser.isActive();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<String> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFacebookURL() {
        return facebookURL;
    }

    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public void setTwitterURL(String twitterURL) {
        this.twitterURL = twitterURL;
    }

    public String getInstagramURL() {
        return instagramURL;
    }

    public void setInstagramURL(String instagramURL) {
        this.instagramURL = instagramURL;
    }

    public String getLinkedinURL() {
        return linkedinURL;
    }

    public void setLinkedinURL(String linkedinURL) {
        this.linkedinURL = linkedinURL;
    }

    public String getGithubURL() {
        return githubURL;
    }

    public void setGithubURL(String githubURL) {
        this.githubURL = githubURL;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
