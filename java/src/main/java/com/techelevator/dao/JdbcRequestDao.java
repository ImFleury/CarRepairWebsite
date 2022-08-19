package com.techelevator.dao;

import com.techelevator.model.Request;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRequestDao implements RequestDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Request getRequestById(int requestId) {
        Request request = null;
        String sql = "SELECT request_id, customer_id, vehicle_id, description FROM requests WHERE request_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestId);
        if (results.next()) {
            request = mapRowToRequest(results);
        }
        return request;
    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> result = new ArrayList<>();
        String sql = "SELECT request_id, customer_id, vehicle_id, description FROM requests";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Request request = mapRowToRequest(rowSet);
            result.add(request);
        }
        return result;
    }

    @Override
    public List<Request> getAllRequests(int customerId) {
        List<Request> result = new ArrayList<>();
        String sql = "SELECT request_id, customer_id, vehicle_id, description FROM requests WHERE customer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, customerId);
        while (rowSet.next()) {
            Request request = mapRowToRequest(rowSet);
            result.add(request);
        }
        return result;
    }

    @Override
    public int addRequest(int customerId, Request request) {
        String sql = "INSERT INTO requests (customer_id, vehicle_id, description) VALUES (?, ?, ?) RETURNING request_id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, customerId, request.getVehicleId(), request.getDescription());
        return id;
    }


    private Request mapRowToRequest(SqlRowSet rs) {
        Request request = new Request();
        request.setRequestId(rs.getInt("request_id"));
        request.setCustomerId(rs.getInt("customer_id"));
        request.setVehicleId(rs.getInt("vehicle_id"));
        request.setDescription(rs.getString("description"));
        return request;
    }


}
