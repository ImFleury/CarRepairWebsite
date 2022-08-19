import axios from "axios";


export default {


    getVehicleYears() {
        return axios.get('/vehicle/years');
    },

    getVehicleMakes() {
        return axios.get('/vehicle/makes');
    },

    getVehicleModels(year, make) {
        return axios.get(`/vehicle/models/${year}/${make}`);
    }


}