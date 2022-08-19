import axios from 'axios';

export default {

   
    getWorkOrders() {
        return axios.get('/employee/workorders').then((response => {
            const workOrder = response;
            return workOrder;
        }))
    },

    //get work orders of logged in user
    getWorkOrdersForUser() {
        return axios.get('/customer/workorders/current')
    },

    getRepairsByWorkOrderId(workOrderId){
        return axios.get(`/customer/workorders/${workOrderId}/repairs`).then((response => {
            const repairs = response;
            return repairs; 
        }))
    },

    addWorkOrder(workOrder) {
        return axios.post('/customer/workorders', workOrder).then((response => {
            const workOrder = response;
            return workOrder;
        }))
    },
    updateWorkOrder(workOrderId, workOrder){
        return axios.put(`/customer/workorders/${workOrderId}`, workOrder).then((response => {
            const workOrder = response;
            return workOrder;
        }))
    },
    updateRepair(repairItemId, repair){
        return axios.put(`/employee/workorders/changerepair/${repairItemId}`, repair).then((response => {
            const repair = response;
            return repair;
        }))
    }, 
    addRepair(repair) {
        return axios.post('/employee/workorders/repairs', repair).then((response => {
            const repair = response;
            return repair;
        }))
    },
}