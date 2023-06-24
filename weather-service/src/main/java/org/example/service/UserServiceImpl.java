package org.example.service;

import org.example.domain.FavoritesLocation;
import org.example.domain.User;
import org.example.repository.FavoritesRepo;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    FavoritesRepo favoritesRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public FavoritesLocation addFav(FavoritesLocation favoritesLocation) {
        return favoritesRepo.save(favoritesLocation);
    }

    @Override
    public List<FavoritesLocation> getAllFav() {
        return favoritesRepo.findAll();
    }

    @Override
    public List<FavoritesLocation> getFavById(int id) {
        return favoritesRepo.findAllByUserId(id);
    }

    @Override
    public void deleteByFavouriteId(int favId
    ) {
        favoritesRepo.deleteById(favId);
    }

    @Override
    public Optional<User> findUserById(int userId) {
        return userRepo.findById(userId);
    }


}
