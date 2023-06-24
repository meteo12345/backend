package org.example.service;

import org.example.domain.Register;
import org.example.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    RegisterRepository repository;

    public Register registerUser(Register user)
    {
        Register userSaved = repository.save(user);
        return userSaved;
    }

    public Register getUserByUsername(String username)  {

        return repository.findByUsername(username);
    }
}
