package com.tutorial.bikeservice.services;

import com.tutorial.bikeservice.entities.Bike;
import com.tutorial.bikeservice.resitories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getUserById(int id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike car){
        return bikeRepository.save(car);
    }
    public List<Bike> byUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
}
