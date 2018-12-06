package io.dataglitter.PostService.controller;

import io.dataglitter.PostService.document.Post;
import io.dataglitter.PostService.dto.HealthDTO;
import io.dataglitter.PostService.dto.ModerationDTO;
import io.dataglitter.PostService.dto.PostDTO;
import io.dataglitter.PostService.dto.PublishedDTO;
import io.dataglitter.PostService.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by <b>Sangram Reddy</b> (ds.sangram.reddy@gmail.com) on 13/12/2017.
 * API's for Post entity
 */
@RestController
@RequestMapping(value = "/api/")
public class PostController {

    private Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * Get the health of the application
     * @return HealthDTO
     */
    @GetMapping(value = "/health")
    public ResponseEntity<HealthDTO> health(){
        return new ResponseEntity<HealthDTO>( new HealthDTO(), HttpStatus.OK);
    }

    /**
     * Create a Post
     * @param postDTO
     * @return Post
     */
    @PostMapping(value = "/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<Post>(postService.save(new Post(postDTO)), HttpStatus.CREATED);
    }

    /**
     * Get post by Id
     * @param id
     * @return Post
     */
    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return new ResponseEntity<Post>(postService.findById(id), HttpStatus.OK);
    }

    /**
     * Update post by Id
     * @param post
     * @return Post
     */
    @PutMapping(value = "/post")
    public ResponseEntity<Post> updateById(@RequestBody Post post){
        return new ResponseEntity<Post>(postService.updateById(post), HttpStatus.ACCEPTED);
    }

    /**
     * Delete post by Id
     * @param id
     * @return String
     */
    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        postService.deleteById(id);
        return new ResponseEntity<String>(id, HttpStatus.NO_CONTENT);
    }

    /**
     * Moderate a post
     * @param moderationDTO
     * @return Post
     */
    @PutMapping(value = "/post/moderate")
    public ResponseEntity<Post> moderateMessage(@RequestBody ModerationDTO moderationDTO){
        Post post = postService.findById(moderationDTO.getId());
        post.setModerated(moderationDTO.isModerated());
        return new ResponseEntity<Post>(postService.save(post), HttpStatus.CREATED);
    }

    /**
     * Publish a post
     * @param publishedDTO
     * @return Post
     */
    @PutMapping(value = "/post/publish")
    public ResponseEntity<Post> publishMessage(@RequestBody PublishedDTO publishedDTO){
        Post post = postService.findById(publishedDTO.getId());
        post.setPublished(publishedDTO.isPublished());
        return new ResponseEntity<Post>(postService.save(post), HttpStatus.CREATED);
    }

    /**
     * Increment liked count
     * @param id
     * @return Long
     */
    @PutMapping(value = "/post/{id}/liked")
    public ResponseEntity<Long> incrementPostLike(@PathVariable String id){
        return new ResponseEntity<Long>(postService.incrementPostLike(id), HttpStatus.ACCEPTED);
    }

    /**
     * Increment shared count
     * @param id
     * @return Long
     */
    @PutMapping(value = "/post/{id}/shared")
    public ResponseEntity<Long> incrementPostShare(@PathVariable String id){
        return new ResponseEntity<Long>(postService.incrementPostShare(id), HttpStatus.ACCEPTED);
    }

    /**
     * Get Latest posts
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/getLatestPosts/{limit}")
    public ResponseEntity<List<Post>> getLatestPosts(@PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getLatestNPosts(limit), HttpStatus.OK);
    }

    /**
     * Get latest posts published and moderated
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/getLatestPosts/{limit}")
    public ResponseEntity<List<Post>> getGlobalLatestPosts(@PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getGlobalLatestNPosts(limit), HttpStatus.OK);
    }

    /**
     * Get latest posts by category
     * @param category
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/getLatestPostsByCategory/{category}/{limit}")
    public ResponseEntity<List<Post>> getLatestPostsByCategory(@PathVariable String category,@PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getNPostsWithCategory(category, limit), HttpStatus.OK);
    }

    /**
     * Get latest posts by category published and moderated
     * @param category
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/getLatestPostsByCategory/{category}/{limit}")
    public ResponseEntity<List<Post>> getGlobalLatestPostsByCategory(@PathVariable String category,@PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getGlobalNPostsWithCategory(category, limit), HttpStatus.OK);
    }

    /**
     * Get latest posts by Author
     * @param authorId
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/getLatestPostsByAuthor/{authorId}/{limit}")
    public ResponseEntity<List<Post>> getNLatestPostsByAuthor(@PathVariable String authorId, @PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getNPostsByAuthorId(authorId, limit), HttpStatus.OK);
    }

    /**
     * Get latest posts by Author published and moderated
     * @param authorId
     * @param limit
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/getLatestPostsByAuthor/{authorId}/{limit}")
    public ResponseEntity<List<Post>> getGlobalNLatestPostsByAuthor(@PathVariable String authorId, @PathVariable Integer limit){
        return new ResponseEntity<List<Post>>(postService.getGlobalNPostsByAuthorId(authorId, limit), HttpStatus.OK);
    }

    /**
     * Get posts with pagination
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/latest/{page}/{size}")
    public ResponseEntity<Page<Post>> getLatestPostsByPageSize(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getLatestPostsByPageSize(page, size), HttpStatus.OK);
    }

    /**
     * Get posts with pagination which are published and moderated
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/latest/{page}/{size}")
    public ResponseEntity<Page<Post>> getGlobalLatestPostsByPageSize(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getGlobalLatestPostsByPageSize(page, size), HttpStatus.OK);
    }

    /**
     * Get posts by category with pagination
     * @param category
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/latest/category/{category}/{page}/{size}")
    public ResponseEntity<Page<Post>> getLatestPostsByCategoryPageSize(@PathVariable String category, @PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getLatestPostsByCategoryPageSize(category, page, size), HttpStatus.OK);
    }

    /**
     * Get posts by category with pagination which are published and moderated
     * @param category
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/latest/category/{category}/{page}/{size}")
    public ResponseEntity<Page<Post>> getGlobalLatestPostsByCategoryPageSize(@PathVariable String category, @PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getGlobalLatestPostsByCategoryPageSize(category, page, size), HttpStatus.OK);
    }

    /**
     * Get posts by author with pagination
     * @param authorId
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/latest/author/{authorId}/{page}/{size}")
    public ResponseEntity<Page<Post>> getLatestPostsByAuthorPageSize(@PathVariable String authorId, @PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getLatestPostsByAuthorPageSize(authorId, page, size), HttpStatus.OK);
    }

    /**
     * Get posts by author with pagination which are published and moderated
     * @param authorId
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/latest/author/{authorId}/{page}/{size}")
    public ResponseEntity<Page<Post>> getGlobalLatestPostsByAuthorPageSize(@PathVariable String authorId, @PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getGlobalLatestPostsByAuthorPageSize(authorId, page, size), HttpStatus.OK);
    }

    /**
     * Get popular posts with pagination
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/popular/{page}/{size}")
    public ResponseEntity<Page<Post>> getPopularPosts(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getPostsByMaxViews(page, size), HttpStatus.OK);
    }

    /**
     * Get popular posts with pagination which are published and moderated
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/popular/{page}/{size}")
    public ResponseEntity<Page<Post>> getGlobalPopularPosts(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getGlobalPostsByMaxViews(page, size), HttpStatus.OK);
    }

    /**
     * Increment page view
     * @param id
     * @return Long
     */
    @PutMapping(value = "/post/{id}/viewed")
    public ResponseEntity<Long> incrementPostView(@PathVariable String id){
        return new ResponseEntity<Long>(postService.incrementPostView(id), HttpStatus.ACCEPTED);
    }

    /**
     * Get unpublished posts with pagination
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/un-published/{page}/{size}")
    public ResponseEntity<Page<Post>> getUnPublishedPosts(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getUnPublishedPosts(page, size), HttpStatus.OK);
    }

    /**
     * Get unmoderated posts with pagination
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/un-moderated/{page}/{size}")
    public ResponseEntity<Page<Post>> getUnModeratedPosts(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getUnModeratedPosts(page, size), HttpStatus.OK);
    }

    /**
     * Get unmoderated and unpublished posts with pagination
     * @param page
     * @param size
     * @return List[Post]
     */
    @GetMapping(value = "/post/global/un-moderated-un-published/{page}/{size}")
    public ResponseEntity<Page<Post>> getUnModeratedUnPublishedPosts(@PathVariable Integer page, @PathVariable Integer size){
        return new ResponseEntity<Page<Post>>(postService.getUnModeratedUnPublishedPosts(page, size), HttpStatus.OK);
    }

    /**
     * Publish a post
     * @param id
     * @return Boolean
     */
    @PutMapping(value = "/post/{id}/publish")
    public ResponseEntity<Boolean> publishPost(@PathVariable String id){
        return new ResponseEntity<Boolean>(postService.publishPost(id), HttpStatus.ACCEPTED);
    }

    /**
     * Unpublish a post
     * @param id
     * @return Boolean
     */
    @PutMapping(value = "/post/{id}/un-publish")
    public ResponseEntity<Boolean> unPublishPost(@PathVariable String id){
        return new ResponseEntity<Boolean>(postService.unPublishPost(id), HttpStatus.ACCEPTED);
    }
}
