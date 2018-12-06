package io.dataglitter.PostService.service;

import io.dataglitter.PostService.document.Post;
import io.dataglitter.PostService.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by reddys on 13/12/2017.
 */
@Component
public class PostService{

    private Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Post updateById(Post post){
        return postRepository.save(post);
    }

    public void deleteById(String id){
        postRepository.delete(id);
    }

    public Post findById(String id){
        return postRepository.findFirstById(id);
    }

    public long incrementPostLike(String id){
        Post post = postRepository.findFirstById(id);
        post.incrementLikedBy();
        return postRepository.save(post).getLikedBy();
    }

    public long incrementPostShare(String id){
        Post post = postRepository.findFirstById(id);
        post.incrementSharedBy();
        return postRepository.save(post).getSharedBy();
    }

    public List<Post> getNPostsWithCategory(String category, int limit){
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public List<Post> getGlobalNPostsWithCategory(String category, int limit){
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public List<Post> getNPostsByAuthorId(String authorId, int limit){
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public List<Post> getGlobalNPostsByAuthorId(String authorId, int limit){
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public void deactivatePostsByAuthorId(String authorId){
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("active").is(true));
        List<Post> posts = mongoTemplate.find(query, Post.class);
        posts.forEach( post -> {
            post.setActive(false);
            postRepository.save(post);
        });
    }

    public void activatePostsByAuthorId(String authorId){
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("active").is(false));
        List<Post> posts = mongoTemplate.find(query, Post.class);
        posts.forEach( post -> {
            post.setActive(true);
            postRepository.save(post);
        });
    }

    public List<Post> getLatestNPosts(int limit){
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.addCriteria(Criteria.where("active").is(true));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public List<Post> getGlobalLatestNPosts(int limit){
        Query query = new Query();
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.limit(limit);
        return mongoTemplate.find(query, Post.class);
    }

    public Page<Post> getLatestPostsByPageSize(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getGlobalLatestPostsByPageSize(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getLatestPostsByCategoryPageSize(String category,int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getGlobalLatestPostsByCategoryPageSize(String category,int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getLatestPostsByAuthorPageSize(String authorId,int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getGlobalLatestPostsByAuthorPageSize(String authorId,int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("authorId").is(authorId));
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getPostsByMaxViews(int page, int size){
        return postRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "viewedBy"));
    }

    public Page<Post> getGlobalPostsByMaxViews(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("moderated").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "viewedBy"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public long incrementPostView(String id){
        Post post = postRepository.findFirstById(id);
        post.incrementViewedBy();
        return postRepository.save(post).getViewedBy();
    }

    public Page<Post> getUnPublishedPosts(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("published").is(false));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getUnModeratedPosts(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("moderated").is(false));
        query.addCriteria(Criteria.where("published").is(true));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public Page<Post> getUnModeratedUnPublishedPosts(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("moderated").is(false));
        query.addCriteria(Criteria.where("published").is(false));
        query.addCriteria(Criteria.where("active").is(true));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }

    public boolean publishPost(String id){
        Post post = postRepository.findFirstById(id);
        post.setModerated(false);
        post.setPublished(true);
        return postRepository.save(post).isPublished();
    }

    public boolean unPublishPost(String id){
        Post post = postRepository.findFirstById(id);
        post.setModerated(false);
        post.setPublished(false);
        return postRepository.save(post).isPublished();
    }

    public Page<Post> getInactivePosts(int page, int size){
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(false));
        query.with(new Sort(Sort.Direction.DESC, "createdOn"));
        query.with(pageable);
        List<Post> list = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(
                list,
                pageable,
                () -> mongoTemplate.count(query, Post.class));
    }
}
