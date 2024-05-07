package com.a18DelSol.PrototypeMP.controller;

import com.a18DelSol.PrototypeMP.repository.RepositoryUser;
import com.a18DelSol.PrototypeMP.service.ServiceUser;
import com.a18DelSol.PrototypeMP.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class ControllerUser {
    @Autowired private RepositoryUser repositoryUser;

    @GetMapping(path="/")
    public @ResponseBody Iterable<ModelUser> userGet () {
        return repositoryUser.findAll();
    }

    @GetMapping(path="/{userID}")
    public @ResponseBody ModelUser userGet(@PathVariable Integer userID) {
        ModelUser modelUserFind = repositoryUser.findById(userID).orElse(null);

        if (modelUserFind == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return modelUserFind;
    }

    @GetMapping(path="/find")
    public @ResponseBody Iterable<ModelUser> userFind (@RequestParam Optional<String> userName,
        @RequestParam Optional<String> userMail, @RequestParam Optional<String> userPass, @RequestParam Optional<LocalDate> userBirth) {
        return repositoryUser.findUser(userName, userMail, userPass, userBirth);
    }

    @PostMapping(path="/")
    public @ResponseBody ArrayList<ModelUser> userPost (@RequestBody ArrayList<ModelUser> modelUserData) {
        ArrayList<ModelUser> returnList = new ArrayList<>();

        for (ModelUser a : modelUserData) {
            returnList.add(ServiceUser.userCreate(a, repositoryUser));
        }

        return returnList;
    }

    /* EXCLUSIVE */

    @PostMapping(path="/signUp")
    public @ResponseBody ResponseEntity<String> userSignUp (@RequestBody ModelUser modelUserData) {
        return ServiceUser.userSignUp(modelUserData, repositoryUser);
    }

    @PostMapping(path="/signIn")
    public @ResponseBody ResponseEntity<ModelUser> userSignIn (@RequestBody ModelUser modelUserData) {
        return ServiceUser.userSignIn(modelUserData, repositoryUser);
    }
}
