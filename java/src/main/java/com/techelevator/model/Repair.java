package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Repair {
    private int repairItemId;
    private int workOrderId;
    @NotEmpty
    private String repairName;
    private double partsCost;
    private double laborCost;
    private boolean completed;

    public Repair() {
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRepairItemId() {
        return repairItemId;
    }

    public void setRepairItemId(int repairItemId) {
        this.repairItemId = repairItemId;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }
}
