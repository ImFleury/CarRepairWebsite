<template>
  <div>
    <h2>Customer Information:</h2>
    <div>{{this.customer.firstName}} {{this.customer.lastName}}</div>
    <div>{{this.customer.phoneNumber}}</div>
    <div>{{this.customer.emailAddress}}</div>
    <h2>Vehicle Information:</h2>
    <div>{{this.vehicle.color}} {{this.vehicle.year}} {{this.vehicle.make}} {{this.vehicle.model}}</div>
    <h2>Customer Description of Issue:</h2>
    <div>{{this.request.description}}</div>
    
  </div>
</template>

<script>

export default {
  data() {
    return {
    customer: {
        id: "",
        username: "",
        authorities: "",
        firstName: "",
        lastName: "",
        emailAddress: "",
        phoneNumber: ""   
    },
    vehicle: {
        vehicleId: "",
        customerId: "",
        make: "",
        model: "",
        year: "",
        color: "",
      },
      request: {
        requestId: "",
        customerId: "",
        vehicleId: "",
        description: ""    
    } 
    };
  },

  created(){
    let request = this.$store.state.requests.find(element => element.requestId === this.$route.params.id);
    this.request = request;

    let vehicle = this.$store.state.vehicles.find(element => element.vehicleId === request.vehicleId);
    this.vehicle = vehicle;

    let customer = this.$store.state.user;
    if (!(this.$store.state.isCustomer)) {
      customer = this.$store.state.users.find(element => element.id === request.customerId);
    }
    this.customer = customer;

  }
};
</script>

<style>
</style>