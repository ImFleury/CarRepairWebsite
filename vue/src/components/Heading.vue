<template>
  <div class="big">
    <div class="logo"><img src="@/assets/AAMKLogoBlue.png" /></div>
    <div class="nav-and-hours">
      <div class="hours">
        <h3 class="hoo">Operating Hours</h3>
        <p><span>Monday-Friday</span> | <span>8am-5pm</span></p>
        <p>Saturday | 10am-4pm</p>
        <p>Sunday | Closed</p>
        <p>Holiday Hours May Vary</p>
      </div>

      <div class="navigation-bar">
        
          <router-link :to="{name: 'WorkOrders'}" v-if="($store.state.isAdmin || $store.state.isEmployee)" class="hov"
            >Home</router-link
          >
          <router-link :to="{name: 'requests'}" v-if="$store.state.isCustomer" class="hov"
            >Home</router-link
          >
          <router-link :to="{name: 'login'}" v-if="!($store.state.isAdmin || $store.state.isEmployee || $store.state.isCustomer)" class="hov"
            >Home</router-link
          >
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            class="hov"
            >Logout</router-link
          >
        
      </div>
    </div>

  </div>
</template>
<script>
export default {
  name: "heading"
  }
</script>


<style>
.big {
  display: flex;
  width: 100%;
  height: 350px;
  background-size: cover;
  background-image: url("https://images.unsplash.com/photo-1486262715619-67b85e0b08d3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80");
  background-repeat: no-repeat;
  column-count: 3;
  column-rule-style: solid;
  column-gap: 40px;
  justify-content: space-around;
  align-items: center;

}
.hours {
  height: 200px;
  width: 400px;
  background: rgb(255, 255, 255, 0.9);
  border: 0.2px solid rgb(255, 255, 255, 0.9);
  border-radius: 30px;
  color: #00008b;
  text-align: center;
  font-family: 'Assistant', Arial, Helvetica, sans-serif;
  padding-bottom: 10px;
}

.container {
  display: flex;
  background-image: url("https://images.unsplash.com/photo-1486262715619-67b85e0b08d3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1332&q=80");
  max-width: 100%;
  position: relative;
  background-repeat: no-repeat;
  flex-wrap: no-wrap;
}

.logo {
  margin: 35px;
  /* position: absolute; */
}

.text-center {
  width: 200px;
}

.hoo {
    font-family: 'Racing Sans One', Arial, Helvetica, sans-serif;

} 
.navigation-bar {
  display: flex;
  justify-content: space-around;
  list-style-type: none;
  max-width: 100%;
  margin: 10px;
  overflow: hidden;
  background-color: #333;
  border: solid 0px black;
  border-radius: 30px;
}

.hov:hover {
  background: #00008b;
  color: white;
}

.hov {
  font-family: 'Assistant', Arial, Helvetica, sans-serif;
  color: white;
  padding: 20px;
  border: 0px solid black;
  text-decoration: none;
}

.nav-and-hours {
  margin: auto;
}
</style>