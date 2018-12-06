package io.dataglitter.PostService.controller;

import io.dataglitter.PostService.document.ApplicationUser;
import io.dataglitter.PostService.dto.*;
import io.dataglitter.PostService.service.ApplicationUserService;
import io.dataglitter.PostService.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

/**
 * Created by <b>Sangram Reddy</b> (ds.sangram.reddy@gmail.com) on 13/12/2017.
 * API's for User entity
 */
@RestController
@RequestMapping(value = "/api/")
public class ApplicationUserController {

    private Logger log = LoggerFactory.getLogger(ApplicationUserController.class);

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private PostService postService;

    /**
     * User sign up
     * @param applicationUser
     * @return ApplicationUserDTO
     */
    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationUserDTO> signUp(@RequestBody ApplicationUser applicationUser) {
        return new ResponseEntity<ApplicationUserDTO>(applicationUserService.createUser(applicationUser), HttpStatus.CREATED);
    }

    /**
     * Validate a token
     * @return Boolean
     */
    @GetMapping("/token/validate")
    public ResponseEntity<Boolean> validateToken() {
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    /**
     * Change password
     * @param passwordDTO
     * @return Boolean
     */
    @PutMapping("/application-user/change-password")
    public ResponseEntity<Boolean> signUp(@RequestBody PasswordDTO passwordDTO) {
        boolean sucessfullyUpdated = applicationUserService.updatePassword(passwordDTO);
        return sucessfullyUpdated?
                new ResponseEntity<Boolean>(sucessfullyUpdated, HttpStatus.ACCEPTED):
                new ResponseEntity<Boolean>(sucessfullyUpdated, HttpStatus.BAD_REQUEST);
    }

    /**
     * Get user by Id
     * @param id
     * @return ApplicationUserDTO
     */
    @GetMapping("/application-user/{id}")
    public ResponseEntity<ApplicationUserDTO> getApplicationUserById(@PathVariable String id){
        return new ResponseEntity<ApplicationUserDTO>(applicationUserService.getApplicationUserById(id), HttpStatus.OK);
    }

    /**
     * Get user by email
     * @param emailDTO
     * @return ApplicationUserDTO
     */
    @PostMapping("/application-user/by-email")
    public ResponseEntity<ApplicationUserDTO> getApplicationUserByEmail(@RequestBody EmailDTO emailDTO){
        return new ResponseEntity<ApplicationUserDTO>(new ApplicationUserDTO(applicationUserService.findByEmail(emailDTO.getEmail())), HttpStatus.OK);
    }

    /**
     * Update user
     * @param applicationUser
     * @return ApplicationUserDTO
     */
    @PutMapping("/application-user")
    public ResponseEntity<ApplicationUserDTO> updateApplicationUser(@RequestBody ApplicationUser applicationUser){
        return new ResponseEntity<ApplicationUserDTO>( applicationUserService.updateApplicationUser(applicationUser),HttpStatus.ACCEPTED);
    }

    /**
     * Delete user
     * @param id
     * @return String
     */
    @DeleteMapping("/application-user/{id}")
    public ResponseEntity<String> deleteApplicationUser(@PathVariable String id){
        applicationUserService.deleteById(id);
        postService.deactivatePostsByAuthorId(id);
        return new ResponseEntity<String>(id, HttpStatus.NO_CONTENT);
    }

    /**
     * deactivate a user
     * @param id
     * @return String
     */
    @PutMapping("/application-user/deactivate/{id}")
    public ResponseEntity<String> deactivateApplicationUser(@PathVariable String id){
        applicationUserService.deactivateApplicationUser(id);
        postService.deactivatePostsByAuthorId(id);
        return new ResponseEntity<String>(id, HttpStatus.ACCEPTED);
    }

    /**
     * Activate a user and his respective posts
     * @param id
     * @return String
     */
    @PutMapping("/application-user/activate/{id}")
    public ResponseEntity<String> activateApplicationUser(@PathVariable String id){
        applicationUserService.activateApplicationUser(id);
        postService.activatePostsByAuthorId(id);
        return new ResponseEntity<String>(id, HttpStatus.ACCEPTED);
    }

    /**
     * Modify user roles
     * @param modifyRolesDTO
     * @return ModifyRolesDTO
     */
    @PutMapping("/application-user/modify-roles")
    public ResponseEntity<ModifyRolesDTO> modifyApplicationUserRoles(@RequestBody ModifyRolesDTO modifyRolesDTO){
        applicationUserService.modifyApplicationUserRoles(modifyRolesDTO);
        return new ResponseEntity<ModifyRolesDTO>( modifyRolesDTO, HttpStatus.ACCEPTED);
    }

    /**
     * Add user follower
     * @param addFollowerDTO
     * @return AddFollowerDTO
     */
    @PutMapping("/application-user/add-follower")
    public ResponseEntity<AddFollowerDTO> addFollower(@RequestBody AddFollowerDTO addFollowerDTO){
        applicationUserService.addFollower(addFollowerDTO);
        return new ResponseEntity<AddFollowerDTO>(addFollowerDTO,HttpStatus.ACCEPTED);
    }

    /**
     * Add a bookmark
     * @param addBookmarkDTO
     * @return AddBookmarkDTO
     */
    @PutMapping("/application-user/add-bookmark")
    public ResponseEntity<AddBookmarkDTO> addBookmark(@RequestBody AddBookmarkDTO addBookmarkDTO){
        applicationUserService.addBookmark(addBookmarkDTO);
        return new ResponseEntity<AddBookmarkDTO>(addBookmarkDTO,HttpStatus.ACCEPTED);
    }

    /**
     * Check if email already exists
     * @param emailDTO
     * @return Long
     */
    @PostMapping("/application-user/check-email")
    public ResponseEntity<Long> checkApplicationUserByEmail(@RequestBody EmailDTO emailDTO){
        return new ResponseEntity<Long>(applicationUserService.checkIfApplicationUserExists(emailDTO.getEmail()), HttpStatus.OK);
    }

    /**
     * Get list of users
     * @param idListDTO
     * @return ApplicationUserDTO
     */
    @PostMapping("/application-user/list")
    public ResponseEntity<HashSet<ApplicationUserDTO>> listOfApplicationUsers(@RequestBody IdListDTO idListDTO) {
        return new ResponseEntity<HashSet<ApplicationUserDTO>>(applicationUserService.listOfApplicationUsers(idListDTO.getIds()), HttpStatus.CREATED);
    }

}
