package com.techelevator.dao;

import com.techelevator.model.Repair;
import com.techelevator.model.Request;
import com.techelevator.model.WorkOrder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcWorkOrderDao implements WorkOrderDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkOrder> getWorkOrders() {
        List<WorkOrder> result = new ArrayList<>();
        String sql = "SELECT work_order_id, request_id, all_completed, time_completed, approved, paid FROM work_orders";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            WorkOrder workOrder = mapRowToWorkOrder(rowSet);
            result.add(workOrder);
        }
        return result;
    }

    @Override
    public WorkOrder getWorkOrder(int workOrderId) {
        WorkOrder workOrder = null;
        String sql = "SELECT * FROM work_orders WHERE work_order_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workOrderId);
        if (results.next()) {
            workOrder = mapRowToWorkOrder(results);
        }
        return workOrder;
    }

    @Override
    public Repair getRepair(int repairId) {
        Repair repair = null;
        String sql = "SELECT * FROM repair_items WHERE repair_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, repairId);
        if (results.next()) {
            repair = mapRowToRepair(results);
        }
        return repair;
    }

    @Override
    public List<WorkOrder> getWorkOrdersByUserId(int userId) {
        List<WorkOrder> result = new ArrayList<>();
        String sql = "SELECT * FROM work_orders" +
                " JOIN requests ON work_orders.request_id = requests.request_id" +
                " JOIN users ON requests.customer_id = users.user_id " +
                " WHERE users.user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()) {
            WorkOrder workOrder = mapRowToWorkOrder(rowSet);
            result.add(workOrder);
        }
        return result;    }

    @Override
    public List<Repair> getRepairsByWorkOrderId(int workOrderId) {
        List<Repair> result = new ArrayList<>();
        String sql = "SELECT * from repair_items where work_order_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, workOrderId);
        while (rowSet.next()){
            Repair repair = mapRowToRepair(rowSet);
            result.add(repair);
        }
        return result;
    }

    @Override
    public int createWorkOrder(WorkOrder workOrder) {
        String sql = "INSERT INTO work_orders (request_id, all_completed, approved, paid) VALUES (?, ?, ?, ?) " +
                "RETURNING work_order_id;";
//        LocalDateTime currentTime = LocalDateTime.now().withNano(0).withSecond(0);
        int workOrderId = jdbcTemplate.queryForObject(sql, Integer.class, workOrder.getRequestId(), workOrder.isAllCompleted(), workOrder.isApproved(), workOrder.isPaid());

        return workOrderId;

    }

    @Override
    public void updateWorkOrder(WorkOrder workOrder) {
        String sql = "UPDATE public.work_orders SET work_order_id=?, request_id=?, all_completed=?, time_completed=?, approved=?, paid =? WHERE work_order_id = ?";
        jdbcTemplate.update(sql, workOrder.getWorkOrderId(), workOrder.getRequestId(), workOrder.isAllCompleted(), workOrder.getTimeCompleted(), workOrder.isApproved(), workOrder.isPaid(), workOrder.getWorkOrderId());
    }

    @Override
    public int createRepair(Repair repair) {
        String sql = "INSERT INTO repair_items (work_order_id, repair_name, parts_cost, labor_cost, completed) VALUES (?, ?, ?, ?, ?) " +
                "RETURNING repair_id;";
        int repairId = jdbcTemplate.queryForObject(sql, Integer.class, repair.getWorkOrderId(), repair.getRepairName(), repair.getPartsCost(), repair.getLaborCost(), repair.isCompleted());

        return repairId;
    }

    @Override
    public void updateRepair(Repair repair) {
        String sql = "UPDATE public.repair_items SET repair_id=?, work_order_id=?, repair_name=?, parts_cost=?, labor_cost=?, completed=? WHERE repair_id=?;";
        jdbcTemplate.update(sql, repair.getRepairItemId(), repair.getWorkOrderId(), repair.getRepairName(), repair.getPartsCost(), repair.getLaborCost(), repair.isCompleted(), repair.getRepairItemId());
    }

    private WorkOrder mapRowToWorkOrder(SqlRowSet rowSet) {
        WorkOrder workorder = new WorkOrder();
        workorder.setWorkOrderId(rowSet.getInt("work_order_id"));
        workorder.setRequestId(rowSet.getInt("request_id"));
        workorder.setAllCompleted(rowSet.getBoolean("all_completed"));

        if (rowSet.getTimestamp("time_completed") != null) {
            workorder.setTimeCompleted(rowSet.getTimestamp("time_completed").toLocalDateTime());
        }
        workorder.setApproved(rowSet.getBoolean("approved"));
        workorder.setPaid(rowSet.getBoolean("paid"));
        return workorder;
    }

    private Repair mapRowToRepair(SqlRowSet rowSet) {
        Repair repair = new Repair();
        repair.setRepairItemId(rowSet.getInt("repair_id"));
        repair.setWorkOrderId(rowSet.getInt("work_order_id"));
        repair.setRepairName(rowSet.getString("repair_name"));
        repair.setPartsCost(rowSet.getDouble("parts_cost"));
        repair.setLaborCost(rowSet.getDouble("labor_cost"));
        repair.setCompleted(rowSet.getBoolean("completed"));
        return repair;
    }

}
