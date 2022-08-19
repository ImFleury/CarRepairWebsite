package com.techelevator.dao;

import com.techelevator.model.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVehicleDao implements VehicleDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcVehicleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Vehicle> getVehicleByCustomerId(int customerId) {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;
        String sql = "SELECT vehicle_id, customer_id, make, model, year, color FROM vehicles WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        while (results.next()) {
            vehicle = mapRowToVehicle(results);
            vehicles.add(vehicle);
        }
        return vehicles;    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = null;

        String sql = "SELECT * FROM vehicles;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            vehicle = mapRowToVehicle(results);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    @Override
    public int addVehicle(int customerId, Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (customer_id, make, model, year, color) VALUES (?, ?, ?, ?, ?) RETURNING vehicle_id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, customerId, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor());
        return id;
    }

    private Vehicle mapRowToVehicle(SqlRowSet results) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(results.getInt("vehicle_id"));
        vehicle.setCustomerId(results.getInt("customer_id"));
        vehicle.setMake(results.getString("make"));
        vehicle.setModel(results.getString("model"));
        vehicle.setYear(results.getInt("year"));
        vehicle.setColor(results.getString("color"));
        return vehicle;
    }
}
