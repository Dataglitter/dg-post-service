package io.dataglitter.PostService.document;

import io.dataglitter.PostService.dto.PostDTO;
import io.dataglitter.PostService.utils.Utilities;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by reddys on 13/12/2017.
 */

@Document(collection="Post")
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;

    private boolean moderated;

    private String title;

    private List<String> tags;

    private String shortDescription;

    private String coverImage;

    private String content;

    private Date createdOn = new Date();

    private String author;

    private String authorId;

    private String category;

    private long likedBy = 0;

    private long sharedBy = 0;

    private long viewedBy = 0;

    private boolean published = false;

    private boolean active = true;

    public Post(){

    }

    public Post(PostDTO postDTO) {
        this.moderated = postDTO.isModerated();
        this.title = postDTO.getTitle();
        this.tags = postDTO.getTags();
        this.shortDescription = postDTO.getShortDescription();
        this.coverImage = postDTO.getCoverImage();
        this.content = postDTO.getContent();
        this.author = postDTO.getAuthor();
        this.authorId = postDTO.getAuthorId();
        this.category = postDTO.getCategory();
        this.likedBy = postDTO.getLikedBy();
        this.sharedBy = postDTO.getSharedBy();
        this.viewedBy = postDTO.getViewedBy();
        this.published = postDTO.isPublished();
        this.active = postDTO.isActive();
    }

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public void incrementLikedBy(){
        this.likedBy++;
    }

    public void incrementSharedBy(){
        this.sharedBy++;
    }

    public void incrementViewedBy(){
        this.viewedBy++;
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

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", moderated=" + moderated +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", shortDescription='" + shortDescription + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", category='" + category + '\'' +
                ", likedBy=" + likedBy +
                ", sharedBy=" + sharedBy +
                ", viewedBy=" + viewedBy +
                ", published=" + published +
                ", active=" + active +
                '}';
    }
}
