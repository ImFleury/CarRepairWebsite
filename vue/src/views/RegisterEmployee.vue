<template>
<div>
  <heading />
  <div id="register">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Register Employee Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label id="account-label" for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label id="account-label" for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
       <label id="account-label" for="username" class="sr-only">First Name</label>
      <input
        type="text"
        id="firstName"
        class="form-control"
        placeholder="First Name"
        v-model="user.firstName"
        required
      />
       <label id="account-label" for="username" class="sr-only">Last Name</label>
      <input
        type="text"
        id="lastName"
        class="form-control"
        placeholder="Last Name"
        v-model="user.lastName"
        required
      />
       <label id="account-label-email-phone" for="username" class="sr-only">Email Address</label>
      <input
        type="text"
        id="emailAddress"
        class="form-control"
        placeholder="Email Address"
        v-model="user.emailAddress"
        required
      />
       <label id="account-label-email-phone" for="username" class="sr-only">Phone Number</label>
      <input
        type="text"
        id="phoneNumber"
        class="form-control"
        placeholder="Phone Number"
        v-model="user.phoneNumber"
        required
      />
        <button id="create-account" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <router-link :to="{ name: 'login' }" id="link-create-account">Have an account?</router-link>
    
    </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import Heading from '../components/Heading.vue';

export default {
  name: 'register-employee',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '',
        emailAddress: '',
        phoneNumber: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  components: {
    Heading
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .registerEmployee(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/workorders',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#register{  
        width: 350px;
        height: 100%;
        overflow: hidden;  
        margin: auto;
        padding: 20px 20px 40px 20px;
        margin-top: 25px;
        background:rgb(255, 255, 255);  
        border: 0px solid  rgb(184, 176, 176);
        border-radius: 15px 35px;
        text-align: center;
        font-family: "Assistant", Arial, Helvetica, sans-serif;
}

#create-account {
  padding: 15px;
  margin-left: 111px;
  margin-top: 20px;
  margin-right: 108px;
  margin-bottom: 12px;
  font-weight: bold;
  border: 1.5px solid silver;
  border-radius: 10px;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
  border-block: 2px;
}

label {
  margin-left: 42px;
  margin-top: 10px;
}

#link-create-account{
margin-left: -2px;
font-family: "Assistant", Arial, Helvetica, sans-serif;
}


</style>
