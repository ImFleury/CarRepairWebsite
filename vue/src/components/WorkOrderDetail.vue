<template>
  <div>
      <form @submit.prevent="updateWorkOrder">
      <h1>Work Order #{{ this.workOrder.workOrderId }}</h1>
      <div>
        <span class="repBold">Approved:</span> {{ approvedStatus(this.workOrder) }}
        <span v-if="$store.state.isCustomer && !(this.workOrder.paid) && $store.state.repairs.length > 0">
          <input class="checker" type="checkbox" v-model="workOrder.approved" @click="flipApprovedStatus" />
        </span>
      </div>
      <div>
        <span class="repBold">Paid:</span> {{ paidStatus(this.workOrder) }}
        <span v-if="(($store.state.isEmployee) || ($store.state.isAdmin) ) && (this.workOrder.approved)">
          <input class="checker" type="checkbox" v-model="workOrder.paid" @click="flipPaidStatus"/>
        </span>
      </div>
      <div><span class="repBold">All Repairs Completed:</span> {{ completedStatus(this.workOrder) }}
        <span v-if="(($store.state.isEmployee) || ($store.state.isAdmin) ) && (this.workOrder.approved)">  
          <input class="checker" type="checkbox" v-model="workOrder.allCompleted" @click="flipCompletedStatus" />
        </span>
      </div>
      <div v-if="($store.state.isEmployee) || ($store.state.isAdmin) || (this.workOrder.timeCompleted) "><span class="repBold">Pick-up Time:</span> {{ displayDate(this.workOrder.timeCompleted)}}
        <span v-if="($store.state.isEmployee) || ($store.state.isAdmin)">
          <input type="datetime-local" v-model="workOrder.timeCompleted">
        </span>
      </div>
      <div v-if="totalCost() > 0">
        <span class="repBold">Total Cost:</span> ${{totalCost().toFixed(2)}}
      </div>
      <input v-if="(!($store.state.isCustomer) || $store.state.repairs.length > 0) && (!($store.state.isCustomer) || !(this.workOrder.allCompleted))" class="buttonstyle" type="submit" value="Save" />
      </form>
  </div>
</template>

<script>
import WorkOrderService from "@/services/WorkOrderService";

export default {
  name: "WorkOrderDetails",
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
    flipApprovedStatus() {
      if (this.workOrder.approved == true) {
        this.workOrder.approved = false;
      } else if (this.workOrder.approved == false) {
        this.workOrder.approved = true;
      }
      
    },
    flipPaidStatus() {
      if (this.workOrder.paid == true) {
        this.workOrder.paid = false;
      } else if (this.workOrder.paid == false) {
        this.workOrder.paid = true;
      }
    
    },
    flipCompletedStatus() {
      if (this.workOrder.allCompleted == true) {
        this.workOrder.allCompleted = false;
      } else if (this.workOrder.allCompleted == false) {
        this.workOrder.allCompleted = true;
        this.$store.state.repairs.forEach(repair => {
          repair.completed = true;

          WorkOrderService.updateRepair(repair.repairItemId, repair);
        })
      }
    
    },
    updateWorkOrder(){
        WorkOrderService.updateWorkOrder(this.workOrder.workOrderId, this.workOrder);
        if (this.$store.state.isCustomer) {
          this.$router.push({name: "requests"});
        } else {
          this.$router.push({name: "WorkOrders"});
        }
    },
    totalCost() {
      let total = 0;
      this.$store.state.repairs.forEach(repair =>
        total += (repair.partsCost + repair.laborCost)
      );

      return total;
    }
  },
  created() {
    this.workOrder = this.$store.state.workOrders.find(element => element.workOrderId === this.$route.params.id)
  },
};
</script>

<style>
</style>