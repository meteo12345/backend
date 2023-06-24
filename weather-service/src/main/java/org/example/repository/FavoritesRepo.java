package org.example.repository;

import org.example.domain.FavoritesLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepo extends JpaRepository<FavoritesLocation, Integer> {
    List<FavoritesLocation> findAllByUserId(int userId);

}
