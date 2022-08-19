package com.techelevator.dao;

import com.techelevator.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> getVehicleByCustomerId(int customerId);

    List<Vehicle> getAllVehicles();

    int addVehicle(int customerId, Vehicle vehicle);
}
