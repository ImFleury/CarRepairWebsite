package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WorkOrderDao;
import com.techelevator.model.Repair;
import com.techelevator.model.User;
import com.techelevator.model.WorkOrder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/customer/workorders")
public class CustomerWorkOrderController {

    private WorkOrderDao workOrderDao;

    private UserDao userDao;

    public CustomerWorkOrderController(WorkOrderDao workOrderDao, UserDao userDao) {
        this.workOrderDao = workOrderDao;
        this.userDao = userDao;
    }

    /**
     * Returns the current logged-in user.
     * @param username The principal username,.
     * @return The current logged-in user.
     */
    public User getLoggedInUser(String username)
    {
        return this.userDao.findByUsername(username);
    }

    /**
     * Gets work orders for logged-in user.
     * @param principal The spring security principal.
     * @return The list of work orders for logged-in user.
     */
    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public List<WorkOrder> getCustomerWorkOrders(Principal principal)
    {
        User loggedInUser = getLoggedInUser(principal.getName());
        return this.workOrderDao.getWorkOrdersByUserId(loggedInUser.getId());
    }


    @RequestMapping(path = "/{workOrderId}/repairs", method = RequestMethod.GET)
    public List<Repair> getRepairsByWorkOrderId(@PathVariable int workOrderId){
        return this.workOrderDao.getRepairsByWorkOrderId(workOrderId);
    }

    @RequestMapping(path = "/{workOrderId}", method = RequestMethod.PUT)
    public void updateWorkOrder(@PathVariable int workOrderId, @RequestBody WorkOrder workOrder){
        workOrder.setWorkOrderId(workOrderId);
        workOrderDao.updateWorkOrder(workOrder);
    }

    @RequestMapping(method = RequestMethod.POST)
    public WorkOrder create(@RequestBody WorkOrder workOrder) {
        int workOrderId = workOrderDao.createWorkOrder(workOrder);
        workOrder.setWorkOrderId(workOrderId);

        return workOrderDao.getWorkOrder(workOrderId);
    }
}
