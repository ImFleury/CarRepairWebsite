<template>
   <div class="workOrders">
    <div v-for="order in inactiveWorkOrders" v-bind:key="order.id">
      <router-link :to="{ name: 'workOrderView', params: {id: order.workOrderId} }">
      #{{order.workOrderId}}: 
      {{customer(request(order.requestId).customerId).lastName}}, 
      {{vehicle(request(order.requestId).vehicleId).color}}
      {{vehicle(request(order.requestId).vehicleId).year}}
      {{vehicle(request(order.requestId).vehicleId).make}}
      {{vehicle(request(order.requestId).vehicleId).model}}
      </router-link>
    </div>
  </div>
</template>

<script>


export default {
name: "WorkOrders",
  data() {
    return {
    };
  },
  methods: {
    request(requestId){
      return this.$store.state.requests.find(request => request.requestId === requestId);
    },
    customer(customerId) {
      return this.$store.state.users.find(customer => customer.id === customerId);
    },
    vehicle(vehicleId) {
      return this.$store.state.vehicles.find(vehicle => vehicle.vehicleId === vehicleId);
    }
  },
  computed: {
     inactiveWorkOrders() {
      return this.$store.state.workOrders.filter(
        workOrder => {
          return workOrder.allCompleted;
        }
      )
    }
  }
}
</script>

<style>

</style>