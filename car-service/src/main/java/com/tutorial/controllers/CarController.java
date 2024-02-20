package com.tutorial.controllers;

import com.tutorial.entities.Car;
import com.tutorial.services.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarServiceImpl carServiceImpl;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carServiceImpl.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getUserById(@PathVariable("id") int id){
        Car car = carServiceImpl.getUserById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car newCar = carServiceImpl.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carServiceImpl.byUserId(userId);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}
