<template>
  <div>
    <heading />
    <history-custie-vehicle />
    <div class="styled-box">
      <div v-if="activeWorkOrders.length > 0">
      <h2>Active Work Orders</h2>
      <div class="item" v-for="orders in activeWorkOrders" v-bind:key="orders.id">
        <h2><router-link :to="{ name: 'workOrderView', params: {id: orders.workOrderId} }"> Order #{{ orders.workOrderId }}</router-link></h2>
        <div>
          <div><span class="repBold">Approved:</span> {{ approvedStatus(orders) }}</div>
          <div><span class="repBold">Paid:</span> {{ paidStatus(orders) }}</div>
          <div><span class="repBold">Completed:</span> {{ completedStatus(orders) }}</div>
          <div v-if="orders.timeCompleted" ><span class="repBold">Pick-up Time:</span> {{ displayDate(orders.timeCompleted)}}</div>
        </div>
      </div>
      </div>
      <div v-else>
        <h2>Active Work Orders</h2>
        <p>No active work orders</p>
      </div>
      <div v-if="inactiveWorkOrders.length > 0">
      <h2>Completed Work Orders</h2>
      <div class="item" v-for="orders in inactiveWorkOrders" v-bind:key="orders.id">
        <h2><router-link :to="{ name: 'workOrderView', params: {id: orders.workOrderId} }"> Order #{{ orders.workOrderId }}</router-link></h2>
        <div>
          <div><span class="repBold">Approved:</span> {{ approvedStatus(orders) }}</div>
          <div><span class="repBold">Paid:</span> {{ paidStatus(orders) }}</div>
          <div><span class="repBold">Completed:</span> {{ completedStatus(orders) }}</div>
          <div v-if="orders.timeCompleted"><span class="repBold">Pick-up Time:</span> {{ displayDate(orders.timeCompleted)}}</div>
        </div>
      </div>
      </div>
      <div v-else>
        <h2>Completed Work Orders</h2>
        <p>No vehicle history</p>
      </div>
    </div>
  </div>
</template>

<script>

import Heading from "../components/Heading.vue";
import HistoryCustieVehicle from "../components/HistoryCustieVehicle.vue";


export default {
  components: {
    Heading,
    HistoryCustieVehicle,
    
  },
  name: "history",
  methods: {
    completedStatus(item) {
      if (item.allCompleted) {
        return 'Yes';
      } else {
        return 'Pending';
      }
    },
    approvedStatus(item) {
       if (item.approved) {
        return 'Yes';
      } else {
        return 'Pending';
      }
    },
    paidStatus(item) {
       if (item.paid) {
        return 'Yes';
      } else {
        return 'Pending';
      }
    },
    displayDate(timestamp) {
      if (timestamp) {
        return new Date(timestamp).toLocaleString();
      }
    },
    workOrdersFiltered(){
      return this.$store.state.workOrders.filter(workOrder => {
      return this.$store.state.requests.some(request => {
        return (workOrder.requestId === request.requestId) &&
          (request.vehicleId === this.$route.params.id); 
   });
  })
    },
  },
  computed: {
    activeWorkOrders() {
      return this.workOrdersFiltered().filter(
        workOrder => {
          return !(workOrder.allCompleted);
        }
      )

    },
    inactiveWorkOrders() {
      return this.workOrdersFiltered().filter(
        workOrder => {
          return workOrder.allCompleted;
        }
      )
    }
  
}
}

</script>

<style>
.item{
   display: flex;
  flex-direction: column;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  border: solid 1px black;
  margin: auto;
  padding: 10px;
  padding-bottom: 35px;
  border-radius: 15px 35px;
}


</style>