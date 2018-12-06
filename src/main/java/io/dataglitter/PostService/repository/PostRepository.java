package io.dataglitter.PostService.repository;

import io.dataglitter.PostService.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * Created by reddys on 13/12/2017.
 */
public interface PostRepository extends MongoRepository<Post,String> {

    Post findFirstById(String id);

}
