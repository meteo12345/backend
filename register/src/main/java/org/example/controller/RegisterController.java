package org.example.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.domain.Register;
import org.example.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody Register registerUser) throws Exception {
        String tempUsername = registerUser.getUsername();
        if (tempUsername != null && !tempUsername.isEmpty()) {
            Register userObj = registerService.getUserByUsername(tempUsername);
            if (userObj != null) {
                throw new Exception("User with username " + tempUsername + " already exists");
            }
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerUser.getPassword());

        Register newUser = new Register(registerUser.getUsername(),registerUser.getEmail(), encodedPassword, encodedPassword);

        Register registeredUser = registerService.registerUser(newUser);
        return new ResponseEntity<Register>(registeredUser, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> logIn(@RequestBody Register loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        Register user1 = registerService.getUserByUsername(username);

        if (user1 == null) {
            return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user1.getPassword())) {
            Map<String, String> tokenMap = new HashMap<String,String>();
            String token = Jwts.builder().setId(user1.getUsername()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "usersecretkey").compact();
            tokenMap.put("token", token);
            tokenMap.put("message", "User successfully logged in");
            return new ResponseEntity<Map<String, String>>(tokenMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Register>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/details/{username}")
    public Register getUserByUsername(@PathVariable String username){
        return registerService.getUserByUsername(username);
    }
}
