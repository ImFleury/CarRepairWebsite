import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  registerCustomer(user) {
    return axios.post('/register-customer', user)
  },

  registerEmployee(user) {
    return axios.post('/register-employee', user)
  },

  getUser(id){
    return axios.get(`/customer/requests/account/${id}`);
  },

  getAllUsers(){
    return axios.get('/employee/requests/account');
  }

}
