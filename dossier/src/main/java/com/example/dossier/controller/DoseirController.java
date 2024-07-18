package com.example.dossier.controller;

import com.example.dossier.modelsDossier.NodesFL;
import com.example.dossier.repositoryDossier.EsfAll2Repo;
import com.example.dossier.repositoryDossier.FlRelativesRepository;
import com.example.dossier.repositoryDossier.MvAutoFlRepo;
import com.example.dossier.repositoryDossier.NewPhotoRepo;
import com.example.security.security.jwt.JwtUtils;
import com.example.security.security.models.User;
import com.example.security.security.repository.RoleRepository;
import com.example.security.security.repository.UserRepository;
import com.example.security.security.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
