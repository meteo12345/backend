package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    @Column(name = "cofirmpassword")
    private String confirmpassword;

    public Register() {
        super();
    }

    public Register(String username, String email, String password, String confirmpassword) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.confirmpassword = confirmpassword;
    }
}
