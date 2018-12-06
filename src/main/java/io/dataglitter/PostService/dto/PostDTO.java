package io.dataglitter.PostService.dto;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by reddys on 13/12/2017.
 */
public class PostDTO {

    private boolean moderated;

    private String title;

    private List<String> tags;

    private String shortDescription;

    private String coverImage;

    private String content;

    private String author;

    private String authorId;

    private String category;

    private long likedBy;

    private long sharedBy;

    private long viewedBy;

    private boolean published;

    private boolean active;

    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(long likedBy) {
        this.likedBy = likedBy;
    }

    public long getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(long sharedBy) {
        this.sharedBy = sharedBy;
    }

    public long getViewedBy() {
        return viewedBy;
    }

    public void setViewedBy(long viewedBy) {
        this.viewedBy = viewedBy;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
