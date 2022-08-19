<template>
  <div>
    <heading />
    <div class="foot">
      <div class="aboutUs">
        <div class="styled-box2" id="about">
          <h1>About Us</h1>
          <p class="aboutContent">
            Never feel stressed about car repair again. AAMK Auto Repair shops
            offer a one-of-a-kind, unrivaled experience provided by our
            friendly, approachable and highly-trained service staff. All of our
            technicians are ASE (Automotive Service Excellence) certified, or
            factory trained, which means they have met the industry standard for
            excellence in automotive repair.
          </p>
        </div>
  
          <form id="login" @submit.prevent="login">
            <h1 id="signin">Sign In</h1>
            <div
              class="alert alert-danger"
              role="alert"
              v-if="invalidCredentials"
            >
              Invalid username and password!
            </div>
            <div
              class="alert alert-success"
              role="alert"
              v-if="this.$route.query.registration"
            >
              Thanks for registering, please sign in.
            </div>
            <label id="label-login" for="username" class="sr-only"
              >Username</label
            >
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
            <label id="label-login" for="password" class="sr-only"
              >Password</label
            >
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
            <button class="sign-in" type="submit">Sign in</button>
            <div>
              <router-link :to="{ name: 'register' }" class="link-need-account"
                >Need an account?</router-link
              >
            </div>
          </form>
        
        <div class="styled-box2">
          <h1>Find Us Here</h1>
          <iframe 
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25623.04038454538!2d-74.00416174759712!3d40.73746757254129!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c259a3b3d04239%3A0xe9d0a453c02726b6!2sAUTO%20SERVICE!5e0!3m2!1sen!2sus!4v1660695609034!5m2!1sen!2sus"
            width="200"
            height="250"
            style="border: 0"
            allowfullscreen=""
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"
          ></iframe>
        </div>
      </div>
      <bottom />
    </div>
  </div>
</template>

<script>
import Heading from "../components/Heading.vue";
import authService from "../services/AuthService";
import RequestService from "@/services/RequestService";
import VehicleService from "@/services/VehicleService";
import WorkOrderService from "@/services/WorkOrderService";
import Bottom from "../components/Bottom.vue";

export default {
  name: "login",
  components: { Heading, Bottom },
  data() {
    return {
      user: {
        username: "",
        password: "",
        role: "",
        userId: "",
        //  firstName: ""
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            if (
              this.$store.state.user.authorities.some(
                (authority) => authority.name === "ROLE_ADMIN"
              )
            ) {
              this.$store.commit("SET_ADMIN", true);
            }
            if (
              this.$store.state.user.authorities.some(
                (authority) => authority.name === "ROLE_EMPLOYEE"
              )
            ) {
              this.$store.commit("SET_EMPLOYEE", true);
            }
            if (
              this.$store.state.user.authorities.some(
                (authority) => authority.name === "ROLE_USER"
              )
            ) {
              this.$store.commit("SET_CUSTOMER", true);
            }

            if (this.$store.state.isAdmin || this.$store.state.isEmployee) {
              authService.getAllUsers().then((response) => {
                this.$store.commit("SET_USERS", response.data);
              });
              RequestService.getEveryRequest().then((response) => {
                this.$store.commit("SET_REQUESTS", response.data);
              });
              VehicleService.getAllVehicles().then((response) => {
                this.$store.commit("SET_VEHICLES", response.data);
              });
              WorkOrderService.getWorkOrders().then((response) => {
                this.$store.commit("SET_WORKORDERS", response.data);
              });
            } else {
              RequestService.getAllRequests().then((response) => {
                this.$store.commit("SET_REQUESTS", response.data);
              });
              VehicleService.getVehicles().then((response) => {
                this.$store.commit("SET_VEHICLES", response.data);
              });
              WorkOrderService.getWorkOrdersForUser().then((response) => {
                this.$store.commit("SET_WORKORDERS", response.data);
              });
            }

            if (this.$store.state.isAdmin || this.$store.state.isEmployee) {
              this.$router.push({ name: "WorkOrders" });
            } else {
              this.$router.push({ name: "requests" });
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
#login {
  width: 250px;
  height: 405px;
  overflow: hidden;
  /* margin: auto; */
  padding: 10px 20px 30px 20px;
  margin-top: 25px;
  background: rgb(255, 255, 255);
  border-radius: 15px 35px;
  text-align: center;
}

.sr-only {
  display: flex;
  width: 300px;
  height: 30px;
  border-radius: 3px;
  padding-top: 8px;
  padding-left: 70px;
  font-size: 17px;
  color: #00008b;
  font-weight: bold;
  margin-left: 16px;
}

.sign-in {
  display: flex;
  margin-top: 25px;
  margin-left: 90px;
  margin-bottom: 15px;
  padding: 9px 14px 9px 14px;
  font-weight: bold;
  border: 1.5px solid silver;
  border-radius: 10px;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  border-block: 2px;
}

#label-login {
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  font-size: 17px;
  color: #00008b;
  /* font-weight: bold; */
  margin-left: 16px;
}

#signin {
  font-family: "Racing Sans One", Arial, Helvetica, sans-serif;
}

.link-need-account {
  font-family: "Assistant", Arial, Helvetica, sans-serif;
}

.aboutUs {
  width: 100%;
  flex-grow: 1;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  gap: 50px;
  flex-wrap: wrap;
}

.styled-box2 {
  font-family: "Racing Sans One", Arial, Helvetica, sans-serif;
  background: rgb(255, 255, 255);
  border: 0px solid rgb(184, 176, 176);
  border-radius: 15px 35px;
  text-align: center;
  width: 250px;
  height: 405px;
  overflow: hidden;
  padding: 10px 20px 30px 20px;
  margin-top: 25px;
  text-decoration: none;
}

.aboutContent {
  justify-content: space-around;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
}

</style>