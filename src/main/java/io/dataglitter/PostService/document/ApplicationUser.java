package io.dataglitter.PostService.document;

import io.dataglitter.PostService.dto.ApplicationUserDTO;
import io.dataglitter.PostService.utils.Utilities;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by reddys on 13/12/2017.
 */
@Document(collection="ApplicationUser")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String email;

    private String password;

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

    private String accountStatus = "IN_USE";

    private boolean active = true;

    public ApplicationUser(){

    }

    public ApplicationUser(ApplicationUserDTO applicationUserDTO){
        this.id = applicationUserDTO.getId();
        this.email = applicationUserDTO.getEmail().toLowerCase();
        this.name = applicationUserDTO.getName();
        this.designation = applicationUserDTO.getDesignation();
        this.followers = applicationUserDTO.getFollowers();
        this.roles = applicationUserDTO.getRoles();
        this.bookmarks = applicationUserDTO.getBookmarks();
        this.about = applicationUserDTO.getAbout();
        this.facebookURL = applicationUserDTO.getFacebookURL();
        this.twitterURL = applicationUserDTO.getTwitterURL();
        this.instagramURL = applicationUserDTO.getInstagramURL();
        this.linkedinURL = applicationUserDTO.getLinkedinURL();
        this.githubURL = applicationUserDTO.getGithubURL();
        this.registeredOn = applicationUserDTO.getRegisteredOn();
        this.profilePicture = applicationUserDTO.getProfilePicture();
        this.accountStatus = applicationUserDTO.getAccountStatus();
        this.active = applicationUserDTO.isActive();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addFollower(String followerId){
        this.followers.add(followerId);
    }

    public void addBookmark(String postId){
        this.bookmarks.add(postId);
    }

    public void convertEmailToLowerCase(){
        this.email.toLowerCase();
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", followers=" + followers +
                ", roles=" + roles +
                ", bookmarks=" + bookmarks +
                ", about='" + about + '\'' +
                ", facebookURL='" + facebookURL + '\'' +
                ", twitterURL='" + twitterURL + '\'' +
                ", instagramURL='" + instagramURL + '\'' +
                ", linkedinURL='" + linkedinURL + '\'' +
                ", githubURL='" + githubURL + '\'' +
                ", registeredOn=" + registeredOn +
                ", profilePicture='" + profilePicture + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", active=" + active +
                '}';
    }
}
