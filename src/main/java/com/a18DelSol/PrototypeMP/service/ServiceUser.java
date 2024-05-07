package com.a18DelSol.PrototypeMP.service;

import com.a18DelSol.PrototypeMP.model.ModelUser;
import com.a18DelSol.PrototypeMP.repository.RepositoryUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ServiceUser {
    /* GENERAL */

    public static ModelUser userCreate(ModelUser user, RepositoryUser repository) {
        repository.save(user);

        return user;
    }

    /* EXCLUSIVE */

    public static ResponseEntity<String> userSignUp(ModelUser user, RepositoryUser repository) {
        ModelUser modelUserFind = repository.findByUserMail(user.getUserMail());

        if (modelUserFind != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail already in use.");
        }

        repository.save(user);

        return new ResponseEntity<>("Sign up OK.", HttpStatus.OK);
    }

    public static ResponseEntity<ModelUser> userSignIn(ModelUser user, RepositoryUser repository) {
        ModelUser modelUserFind = repository.findByUserMail(user.getUserMail());

        if (modelUserFind == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");
        } else if (!modelUserFind.getUserPass().equals(user.getUserPass())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass is incorrect.");
        }

        return new ResponseEntity<>(modelUserFind, HttpStatus.OK);
    }
}
