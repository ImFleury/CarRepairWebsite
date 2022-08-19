package com.techelevator.dao;

import com.techelevator.model.Repair;
import com.techelevator.model.WorkOrder;

import java.util.List;

public interface WorkOrderDao {

    List<WorkOrder> getWorkOrders();

    WorkOrder getWorkOrder(int workOrderId);

    Repair getRepair(int repairId);

    List<WorkOrder> getWorkOrdersByUserId(int userId);

    List<Repair> getRepairsByWorkOrderId(int workOrderId);

    int createWorkOrder(WorkOrder workOrder);

    void updateWorkOrder(WorkOrder workOrder);

    int createRepair(Repair repair);

    void updateRepair(Repair repair);
}
