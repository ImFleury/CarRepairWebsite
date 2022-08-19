package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WorkOrderDao;
import com.techelevator.model.Repair;
import com.techelevator.model.User;
import com.techelevator.model.WorkOrder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated() && (hasRole('ROLE_EMPLOYEE') || hasRole('ROLE_ADMIN'))")
@RequestMapping(path = "/employee/workorders")
public class EmployeeWorkOrderController {

    private WorkOrderDao workOrderDao;

    private UserDao userDao;

    public EmployeeWorkOrderController(WorkOrderDao workOrderDao, UserDao userDao) {
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

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkOrder> getWorkOrders()
    {
        return this.workOrderDao.getWorkOrders();
    }


    @RequestMapping(path = "/repairs", method = RequestMethod.POST)
    public Repair createRepair(@Valid @RequestBody Repair repair) {

        int repairId = workOrderDao.createRepair(repair);
        repair.setRepairItemId(repairId);

        return workOrderDao.getRepair(repairId);
    }


    @RequestMapping(path="/changerepair/{repairItemId}", method = RequestMethod.PUT)
    public void updateRepair(@PathVariable int repairItemId, @RequestBody Repair repair){
    workOrderDao.updateRepair(repair);
    }

}
