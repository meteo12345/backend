package org.example.repository;

import org.example.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

    public List<Register> findByUsernameAndPassword(String username, String password);
    public Register findByUsername(String username);

}
