<template>
  <div class="vehicle">
  
    <div v-for="vehicle in this.$store.state.vehicles" v-bind:key="vehicle.vehicleId" class="vehicle">
      <router-link v-bind:to="{name : 'history', params: {id: vehicle.vehicleId}}">{{vehicle.year}} {{vehicle.make}} {{vehicle.model}} {{ vehicle.color }}</router-link>
    </div>
    <div v-if="$store.state.vehicles.length === 0">No Vehicle History</div>
    
  </div>
</template>

<script>


export default {
  name: "Vehicle",

 
};
</script>

<style>
</style>