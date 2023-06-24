package org.example.service;

import org.example.domain.FavoritesLocation;
import org.example.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    FavoritesLocation addFav(FavoritesLocation favoritesLocation);

    List<FavoritesLocation> getAllFav();

    List<FavoritesLocation> getFavById(int id);

    void deleteByFavouriteId(int favId);

    Optional<User> findUserById(int userId);
}
