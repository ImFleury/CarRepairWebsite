<template>
  <div>
    <heading />
    <div class="styled-box"><customer-vehicle-info /></div>
    <div class="styled-box"><work-order-detail /></div>
    <div class="styled-box" id="repairs" v-if="$store.state.repairs.length > 0"><repair-list /></div>
    <div class="styled-box"  v-if="($store.state.isEmployee) || ($store.state.isAdmin)"><repair-request /></div>
  </div>
</template>

<script>
import RepairList from "@/components/RepairList";
import WorkOrderDetail from "../components/WorkOrderDetail.vue";
import Heading from "../components/Heading.vue";
import RepairRequest from "../components/RepairRequest.vue";
import CustomerVehicleInfo from "../components/CustomerVehicleInfo.vue";
import WorkOrderService from "../services/WorkOrderService";

export default {
  name: "work-order-view",
  components: {
    RepairList,
    WorkOrderDetail,
    Heading,
    RepairRequest,
    CustomerVehicleInfo
  },
  created(){
    WorkOrderService.getRepairsByWorkOrderId(this.$route.params.id).then(
      (response) => {
        this.$store.commit("SET_REPAIRS", response.data);
      }
    );
  }
};
</script>

<style>

</style>