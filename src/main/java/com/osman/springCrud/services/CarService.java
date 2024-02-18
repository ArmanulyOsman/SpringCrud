package com.osman.springCrud.services;

import com.osman.springCrud.entity.Car;
import com.osman.springCrud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {
    @Autowired
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car show(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void updateCar(int id, Car newCar) {
        newCar.setId(id);
        carRepository.save(newCar);
    }
    @Transactional
    public void delete(int id) {
        carRepository.deleteById(id);
    }
}
