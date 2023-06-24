package org.example.controller;

import org.example.domain.FavoritesLocation;
import org.example.domain.User;
import org.example.dto.FavoriteRequest;
import org.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/weather")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/fav")
    public ResponseEntity<FavoritesLocation> addFav(@RequestBody FavoriteRequest request)
    {
        var optional = userService.findUserById(request.userId());
        if(optional.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var fav = FavoritesLocation.builder().cityName(request.cityName()).user(optional.get()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addFav(fav));
    }

    @GetMapping("/allfav")
    public ResponseEntity<List<FavoritesLocation>> getAllFav(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllFav());
    }

    @GetMapping("/allfav/{id}")
    public ResponseEntity<List<FavoritesLocation>> getALlFavById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getFavById(id));
    }

    @DeleteMapping("/deletefav/{favId}")
    public ResponseEntity deleteFavourite(@PathVariable int favId){
        userService.deleteByFavouriteId(favId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}