<template>
  <div>
    <form class="submitForm" v-on:submit.prevent="addRequest">
      <label class="request-form-label">Vehicle
        <select v-model="request.vehicleId">
          <option
            v-for="vehicle in this.$store.state.vehicles"
            v-bind:key="vehicle.vehicleId"
            :value="vehicle.vehicleId"
          >
          {{vehicle.year}} {{vehicle.make}} {{vehicle.model}} {{ vehicle.color }}
          </option>
        </select>
      </label>
      <p></p>
      <label class="request-form-label">Description of Issues:</label>
      <textarea class="input-text-area" v-model="request.description"></textarea>

      <input class="buttonstyle" type="submit" value="Save" />
    </form>
  </div>
</template>

<script>
import RequestService from "@/services/RequestService";
import WorkOrderService from "@/services/WorkOrderService";


export default {
  data() {
    return {
      request: {
        vehicleId: "",
        description: "",
      },
      workOrder: {
        requestId: "",
        allCompleted: false,
        timeCompleted: "",
        approved: false,
        paid: false,
      },
      customerVehicles: []
    };
  },
  name: "SubmitRequest",
  methods: {
    addRequest() {
      RequestService.addRequest(this.request).then((response) => {
        if (response.status === 201 || response.status === 200) {

          // Adds repair request to vue store
          const requests = [...this.$store.state.requests]
          requests.push(response.data);
          this.$store.commit("SET_REQUESTS", requests);

          this.workOrder.requestId = response.data.requestId;

          // reset the form
          this.request = {}
          this.addWorkOrder();
        }
      });
    },
    addWorkOrder() {
      WorkOrderService.addWorkOrder(this.workOrder).then((response) => {
        if (response.status === 201 || response.status === 200) {

          // Adds repair request to vue store
          const listWorkOrders = [...this.$store.state.workOrders]
          listWorkOrders.push(response.data);
          this.$store.commit("SET_WORKORDERS", listWorkOrders);


          // reset the form
          this.workOrder = {}
        }
      });
    },
  }
};
</script>

<style>

.request-form-label{
  margin-right: 24px;
  margin-bottom: 20px;
  margin-top: 20px;
  font-size: 17px;
  color: #00008b;
  font-weight: bold;
  }

.input {
  display: flex;
  width: 95%;
  height: 25px;
  margin-right: 10px;
  margin-left: 5px;
  margin-top: 20px;
  margin-bottom: 15px;
  /* flex-direction: column; */
}

.input-text-area{
  display: flex;
  width: 95%;
  height: 100px;
  margin-right: 10px;
  margin-left: 5px;
  margin-top: 20px;
  margin-bottom: 15px;
  border: 2px solid silver;
  border-radius: 10px;
}

.text {
  font-family: Arial, Helvetica, sans-serif;
} 
</style>