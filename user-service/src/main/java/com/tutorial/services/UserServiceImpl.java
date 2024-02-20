package com.tutorial.services;


import com.tutorial.entities.Users;
import com.tutorial.feignclients.BikeFeignClient;
import com.tutorial.feignclients.CarFeignClient;
import com.tutorial.models.Bike;
import com.tutorial.models.Car;
import com.tutorial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;



    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Users getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public Users save(Users user){
       return userRepository.save(user);
    }

    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/"+ userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+ userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId,Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId,Bike bike){
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "no existe el usuario");
            return result;
        }

        result.put("User",user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars.isEmpty()){
            result.put("Cars","Ese user no tiene coches");
        }
        else{
            result.put("Cars",cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty()){
            result.put("Bikes","Ese user no tiene motos");
        }
        else{
            result.put("Bikes",bikes);
        }

        return result;
    }
}
