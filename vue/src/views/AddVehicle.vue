<template>
  <div>
    <heading />
    <div class="styled-box">
      <h2>Submit a New Vehicle</h2>
      <add-vehicle />
    </div>
  </div>
</template>

<script>
import AddVehicle from "../components/AddVehicle.vue";
import Heading from '../components/Heading.vue';
export default {
  components: { AddVehicle,
  Heading },
};
</script>

<style>
</style>