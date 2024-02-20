package com.tutorial.services;

import com.tutorial.entities.Car;
import com.tutorial.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getUserById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        return carRepository.save(car);
    }
    public List<Car> byUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
