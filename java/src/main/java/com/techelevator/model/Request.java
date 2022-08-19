package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class Request {

    private int requestId;
    private int customerId;
    private int vehicleId;
    @NotEmpty
    private String description;

    public Request() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int repairId) {
        this.requestId = repairId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", description='" + description + '\'' +
                '}';
    }
}
