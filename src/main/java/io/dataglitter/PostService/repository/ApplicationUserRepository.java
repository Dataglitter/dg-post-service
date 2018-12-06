package io.dataglitter.PostService.repository;

import io.dataglitter.PostService.document.Post;
import io.dataglitter.PostService.document.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by reddys on 13/12/2017.
 */
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser,String> {
    ApplicationUser findByEmail(String email);
    ApplicationUser findById(String id);
}
