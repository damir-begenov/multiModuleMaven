package com.example.security.controller;

import com.example.security.payload.request.LoginRequest;
import com.example.security.payload.request.SignupRequest;
import com.example.security.payload.response.JwtResponse;
import com.example.security.payload.response.MessageResponse;
import com.example.security.security.jwt.JwtUtils;
import com.example.security.security.models.ERole;
import com.example.security.security.models.Role;
import com.example.security.security.models.User;
import com.example.security.security.repository.RoleRepository;
import com.example.security.security.repository.UserRepository;
import com.example.security.security.services.UserDetailsImpl;
import com.example.security.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3000)
@RestController
@RequestMapping("/api/finpol/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;
  @Autowired

  UserDetailsServiceImpl userDetailsService;
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }
  @PostMapping("/changePassword")
  public void changePassword( @RequestParam String password, Principal principal, @RequestParam String username){
//    System.out.println(userDetailsService.loadUserByUsernamek(principal));
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      user.get().setPassword(encoder.encode(password));
      System.out.println(user.get().getUsername());
      System.out.println(user.get().getEmail());
      System.out.println(password);
      userRepository.save(user.get());
    }
  }
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getEmail(),
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    ;

//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ADMIN)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.VIP)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.LEVEL_3_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }

    if (signUpRequest.getLevel().equals("2")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_2_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }
    if (signUpRequest.getLevel().equals("1")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_1_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("3")) {
      Role userRole = roleRepository.findByName(ERole.LEVEL_3_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("vip")) {
      Role userRole = roleRepository.findByName(ERole.VIP)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }if (signUpRequest.getLevel().equals("admin")) {
      Role userRole = roleRepository.findByName(ERole.ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);  }
    user.setActive(true);
//    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    if(fileName.contains(".."))
//    {
//      System.out.println("not a a valid file");
//    }
//    try {
//      user.setUser_photo(Base64.getEncoder().encodeToString(file.getBytes()));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
