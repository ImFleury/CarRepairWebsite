package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.Date;

public class WorkOrder {

    private int workOrderId;
    private int requestId;
    private boolean allCompleted;
    private LocalDateTime timeCompleted;
    private boolean approved;
    private boolean paid;

    public WorkOrder() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    public boolean isAllCompleted() {
        return allCompleted;
    }

    public void setAllCompleted(boolean allCompleted) {
        this.allCompleted = allCompleted;
    }

    public LocalDateTime getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(LocalDateTime timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
