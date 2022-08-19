import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import RequestView from '../views/RequestView.vue'
import RegisterEmployee from '../views/RegisterEmployee'
import WorkOrders from '../views/WorkOrders'
import WorkOrderView from '../views/WorkOrderView'
import AddVehicle from '../views/AddVehicle'
import History from '../views/History'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/requests',
      name: 'requests', 
      component: RequestView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/addVehicle',
      name: 'add-vehicle',
      component: AddVehicle,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/registerEmployee',
      name: 'registerEmployee',
      component: RegisterEmployee,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/workorders',
      name: 'WorkOrders',
      component: WorkOrders,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/workorders/:id',
      name: 'workOrderView',
      component: WorkOrderView,
      meta: {
        requiresAuth: true
      }
    },
      {
        path: '/history/:id',
        name: 'history',
        component: History,
        meta: {
          requiresAuth: true
        }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
