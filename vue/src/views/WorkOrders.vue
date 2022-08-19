<template>
<div>
  <heading />
  <div class="styled-box">
      <h1>Hello {{ this.$store.state.user.firstName }} {{this.$store.state.user.lastName}} </h1>
      <p> Your Employee ID is: {{ this.$store.state.user.id }} </p>
      <p></p>
      <router-link v-if="this.$store.state.isAdmin"
        :to="{ name: 'registerEmployee' }"
        >Register an Employee</router-link>
  </div>
  <div class="styled-box">
    <h1>Open Work Orders</h1>
    <work-orders />
  </div>
  <div class="styled-box">
    <h1>Closed Work Orders</h1>
    <work-orders-inactive />
  </div>
</div>
</template>

<script>
import Heading from '../components/Heading.vue'
import WorkOrders from '../components/WorkOrders.vue'
import WorkOrdersInactive from '../components/WorkOrdersInactive.vue'
export default {
  components: { 
    WorkOrders,
    Heading,
    WorkOrdersInactive,

      },

}
</script>

<style>

h1 {
text-align: center;
} 

</style>