package io.dataglitter.PostService.service;

import io.dataglitter.PostService.document.ApplicationUser;
import io.dataglitter.PostService.dto.*;
import io.dataglitter.PostService.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.StreamSupport;
/**
 * Created by reddys on 13/12/2017.
 */

@Component
public class ApplicationUserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserDTO createUser(ApplicationUser applicationUser){
        applicationUser.convertEmailToLowerCase();
        applicationUser.setPassword(encryptor(applicationUser.getPassword()));
        return new ApplicationUserDTO(applicationUserRepository.save(applicationUser));
    }

    public ApplicationUserDTO getApplicationUserById(String id){
        return new ApplicationUserDTO(applicationUserRepository.findById(id));
    }

    public ApplicationUser findByEmail(String email){
        return applicationUserRepository.findByEmail(email);
    }

    public boolean updatePassword(PasswordDTO passwordDTO){
        ApplicationUser applicationUser = getApplicationUserByIdForProcessing(passwordDTO.getId());
        if (matchOldPassword(passwordDTO.getOldPassword(), applicationUser.getPassword())){
            applicationUser.setPassword(encryptor(passwordDTO.getNewPassword()));
            applicationUserRepository.save(applicationUser);
            return true;
        }else{
            return false;
        }
    }

    public ApplicationUserDTO updateApplicationUser(ApplicationUser applicationUser){
        applicationUser.convertEmailToLowerCase();
        applicationUser.setPassword(getPassword(applicationUser.getId()));
        applicationUserRepository.save(applicationUser);
        return new ApplicationUserDTO(applicationUser);
    }

    public void addFollower(AddFollowerDTO addFollowerDTO){
        ApplicationUser applicationUser = applicationUserRepository.findById(addFollowerDTO.getId());
        applicationUser.addFollower(addFollowerDTO.getFollowerId());
        applicationUserRepository.save(applicationUser);
    }

    public void addBookmark(AddBookmarkDTO addBookmarkDTO){
        ApplicationUser applicationUser = applicationUserRepository.findById(addBookmarkDTO.getId());
        applicationUser.addBookmark(addBookmarkDTO.getPostId());
        applicationUserRepository.save(applicationUser);
    }

    public void modifyApplicationUserRoles(ModifyRolesDTO modifyRolesDTO){
        ApplicationUser applicationUser = applicationUserRepository.findById(modifyRolesDTO.getId());
        applicationUser.setRoles(modifyRolesDTO.getRoles());
        applicationUserRepository.save(applicationUser);
    }

    public long checkIfApplicationUserExists(String email){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.count(query, ApplicationUser.class);
    }

    public void deleteById(String id){
        applicationUserRepository.delete(id);
    }

    public HashSet<ApplicationUserDTO> listOfApplicationUsers (List<String> ids){
        Iterable<ApplicationUser> listOfApplicationUsers = applicationUserRepository.findAll(ids);

        HashSet<ApplicationUserDTO> listOfApplicatinUsers = new HashSet<ApplicationUserDTO>();
        StreamSupport.stream(listOfApplicationUsers.spliterator(), false)
                .forEach((applicationUser) -> listOfApplicatinUsers.add( new ApplicationUserDTO(applicationUser)));

        return listOfApplicatinUsers;
    }

    public void deactivateApplicationUser(String id){
        ApplicationUser applicationUser = applicationUserRepository.findById(id);
        applicationUser.setActive(false);
        applicationUserRepository.save(applicationUser);
    }

    public void activateApplicationUser(String id){
        ApplicationUser applicationUser = applicationUserRepository.findById(id);
        applicationUser.setActive(true);
        applicationUserRepository.save(applicationUser);
    }

    private ApplicationUser getApplicationUserByIdForProcessing(String id){
        return applicationUserRepository.findById(id);
    }

    private String encryptor(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    private String getPassword(String id){
        return applicationUserRepository.findById(id).getPassword();
    }

    private boolean matchOldPassword(String oldPasswordFromRequest, String encodedPassword){
        return bCryptPasswordEncoder.matches(oldPasswordFromRequest, encodedPassword);
    }

}
