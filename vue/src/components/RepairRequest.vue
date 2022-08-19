<template>
  <div>
    <form @submit.prevent="addRepair">
      <p class="orderForm">Repair Name:</p>
      <input type="text" v-model="repair.repairName" />
      <p></p>
      <p class="orderForm">Parts Cost:</p>
      <input type="text" v-model="repair.partsCost" />
      <p></p>
      <p class="orderForm">Labor Cost:</p>
      <input type="text" v-model="repair.laborCost" />
      <p></p>
      <p class="orderForm">Completed?</p>
      <input type="checkbox" v-model="repair.completed" />
      <p class="orderForm"></p>
      <input class="buttonstyle" type="submit" value="Save" />
    </form>
  </div>
</template>

<script>
import WorkOrderService from "../services/WorkOrderService";
export default {
  data() {
    return {
      repair: {
        workOrderId: this.$route.params.id,
        repairName: "",
        partsCost: "",
        laborCost: "",
        completed: false,
      },
      workOrder: {
        workOrderId: this.$route.params.id,
        requestId: "",
        allCompleted: "",
        timeCompleted: "",
        approved: "",
        paid: "",
      },
    };
  },
  name: "SubmitRepair",
  methods: {
    addRepair() {
      WorkOrderService.addRepair(this.repair).then((response) => {
        if (response.status === 201 || response.status === 200) {
         
           const repairs = [...this.$store.state.repairs]
           repairs.push(response.data);
           this.$store.commit("SET_REPAIRS", repairs);

          this.repair = {
            workOrderId: this.$route.params.id,
            repairName: "",
            partsCost: "",
            laborCost: "",
            completed: false,
          };

          this.checkRepairListCompleted();
        }
      });
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
  },
   created() {
    this.workOrder = this.$store.state.workOrders.find(element => element.workOrderId === this.$route.params.id);
  }
};
</script>

<style>
.orderForm {
  text-align: center;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  font-weight: bold;
  color: hsl(240, 100%, 27%);
}
</style>