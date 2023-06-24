package org.example.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue

    private int id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<FavoritesLocation> favoritesLocationList;
}
