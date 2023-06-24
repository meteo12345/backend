package org.example.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Embeddable
public class FavoritesLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fav_Id")
    private int favId;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    @JoinColumn(name="id")
    @JsonIgnore
    private User user;
}

