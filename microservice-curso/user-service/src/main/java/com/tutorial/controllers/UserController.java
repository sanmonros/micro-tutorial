package com.tutorial.controllers;


import com.tutorial.entities.Users;
import com.tutorial.models.Bike;
import com.tutorial.models.Car;
import com.tutorial.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<Users>> getAll(){
        List<Users> users = userServiceImpl.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") int id){
        Users user = userServiceImpl.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody Users user){
        Users userNew = userServiceImpl.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        Users user = userServiceImpl.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userServiceImpl.getCars(userId);
        return ResponseEntity.ok(cars);

    }

    @GetMapping("/bikes{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        Users user = userServiceImpl.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        List<Bike> bikes = userServiceImpl.getBikes(userId);
        return ResponseEntity.ok(bikes);

    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId,@RequestBody Car car){
        if(userServiceImpl.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        Car carNew = userServiceImpl.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId,@RequestBody Bike bike){
        if(userServiceImpl.getUserById(userId) == null){
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userServiceImpl.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String,Object> result = userServiceImpl.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }



}
