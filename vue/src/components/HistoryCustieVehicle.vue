<template>
  <div class="styled-box">
    <h1>Vehicle History Report</h1>
    <p></p>
    <h2>Customer Information:</h2>
    <div>{{this.customer.firstName}} {{this.customer.lastName}}</div>
    <div>{{this.customer.phoneNumber}}</div>
    <div>{{this.customer.emailAddress}}</div>
    <h2>Vehicle Information:</h2>
    <div>{{this.vehicle.color}} {{this.vehicle.year}} {{this.vehicle.make}} {{this.vehicle.model}}</div>
    
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
      }
    };
  },

  created(){
    let vehicle = this.$store.state.vehicles.find(element => element.vehicleId === this.$route.params.id);
    this.vehicle = vehicle;

    let customer = this.$store.state.user;
    if (!(this.$store.state.isCustomer)) {
      customer = this.$store.state.users.find(element => element.id === vehicle.customerId);
    }
    this.customer = customer;

  }
};
</script>

<style>
</style>