package com.example.dossier.dossier.controller;

import com.example.security.security.models.User;
import com.example.dossier.dossier.modelsDossier.*;
import com.example.dossier.dossier.repositoryDossier.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3000)
public class DoseirController {
    @Autowired
    NewPhotoRepo newPhotoRepo;
    @Autowired
    EsfAll2Repo esfAll2Repo;
    @Autowired
    MvAutoFlRepo mvAutoFlRepo;
    @Autowired
    FlRelativesRepository relativesRepository;


    @GetMapping("/profile")
    public NodesFL getProfile(@RequestParam String iin, Principal principal) {
        System.out.println(principal.getName());
        User user = new User();
        System.out.println(user);
        return new NodesFL();
    }
}
