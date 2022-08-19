<template>
  <div>
    <h2>Repairs</h2>
    <ul>
      <li
        class="repair"
        v-for="repair in $store.state.repairs"
        v-bind:key="repair.id"
      >
        <p><span class="repBold">Repair:</span> {{ repair.repairName }}</p>
        
        <p><span class="repBold">Parts Cost:</span> ${{ repair.partsCost.toFixed(2) }}</p>
        
        <p><span class="repBold">Labor Cost:</span> ${{ repair.laborCost.toFixed(2) }}</p>
        
        <p>
          <span class="repBold">Completed:</span> {{ completedStatus(repair) }}
          <input
            v-if="$store.state.isEmployee || $store.state.isAdmin"
            class="checker"
            type="checkbox"
            v-model="repair.completed"
            v-on:click="updateRepair(repair)"
          />
        </p>

      </li>
    </ul>
  </div>
</template>

<script>
import WorkOrderService from "../services/WorkOrderService";
export default {
  name: "repair-list",
  data() {
    return {
      workOrder: {
        workOrderId: "",
        requestId: "",
        allCompleted: "",
        timeCompleted: "",
        approved: "",
        paid: "",
      },
    };
  },
  methods: {
    updateRepair(repair) {
      if (repair.completed == true) {
        repair.completed = false;
      } else if (repair.completed == false) {
        repair.completed = true;
      }

      WorkOrderService.updateRepair(repair.repairItemId, repair);

      this.checkRepairListCompleted();
    

    },
    checkRepairListCompleted() {
      if (this.$store.state.repairs.every(repair => {
        return repair.completed})) {
        this.workOrder.allCompleted = true;
        WorkOrderService.updateWorkOrder(this.workOrder.workOrderId, this.workOrder);
      } else {
        this.workOrder.allCompleted = false;
        WorkOrderService.updateWorkOrder(this.workOrder.workOrderId, this.workOrder)
      }
    },
    completedStatus(item) {
      if (item.completed) {
        return 'Yes';
      } else {
        return 'Pending';
      }
    }
  },
  created() {
    this.workOrder = this.$store.state.workOrders.find(element => element.workOrderId === this.$route.params.id);
  }
};
</script>

<style>
.repair {
  display: flex;
  flex-direction: column;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  border: solid 1px black;
  margin: 20px 25px 10px -10px;
  padding: 10px;
  border-radius: 15px 35px;
}

.repBold{
  font-weight: bold;
  color: hsl(240, 100%, 27%);
}
</style>