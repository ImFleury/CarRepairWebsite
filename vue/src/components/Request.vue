<template>
  <div class="requests">
    <div  v-for="request in this.$store.state.requests" v-bind:key="request.id">
      <router-link :to="{ name: 'workOrderView', params: {id: request.requestId} }">
      {{request.description}} ({{vehicle(request.vehicleId).year}} {{vehicle(request.vehicleId).make}}
      {{vehicle(request.vehicleId).model}})</router-link>
    </div>
    <div v-if="$store.state.requests.length === 0">No Repair Request History</div>
  </div>
</template>

<script>
import RequestService from "@/services/RequestService";

export default {
  name: "Request",
  methods: {
    vehicle(vehicleId) {
      return this.$store.state.vehicles.find(vehicle => vehicle.vehicleId === vehicleId);
    }
  },
  created() {
    
    RequestService.getAllRequests(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_REQUESTS", response.data);
    });
  },
};
</script>

<style>

</style>