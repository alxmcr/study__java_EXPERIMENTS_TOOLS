<template>
    <div id="card-welcome">
        <font-awesome-icon icon="check-circle" />
        <h1 class="card-title card-home-title">{{user.displayName}}</h1>
        <div class="card-body">
            <span class="card-description">Signup succeeded</span>
            <span class="card-text card-home-text">{{user.email}}</span>
        </div>
        <div class="todoapp-group-card">
            <button @click="goToDashboard" type="button" class="todoapp-button todoapp-button-success card-button">Continue</button>
            <button @click="logOut" type="button" class="todoapp-button todoapp-button-danger card-button">Logout</button>
        </div>
    </div>
</template>

<script>
import ButtonGroupActionsWelcome from '@/components/ButtonGroup/ButtonGroupActionsWelcome'
// Firebase
import firebase from 'firebase'
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'CardWelcome',
  components: {
    FontAwesomeIcon,
    ButtonGroupActionsWelcome
  },
  data: function () {
    return {
      error: {},
      user: {}
    }
  },
  beforeCreate: function () {
    var cardWelcomeView = this
    firebase.auth().onAuthStateChanged(function (user) {
      if (user) {
        cardWelcomeView.user = user
      } else {
        // No Login
        cardWelcomeView.$router.push({
          path: '/'
        })
      }
    })
  },
  methods: {
    goToDashboard: function () {
      this.$router.push({
        path: '/dashboard'
      })
    },
    logOut: function () {
      let cardWelcomeView = this
      firebase.auth().signOut().then(function () {
        // Sign-out successful.
        console.log('Sign-out successful.')
      }).catch(function (error) {
        // An error happened.
        cardWelcomeView.error = error
        console.error(error)
      })
    }
  }
}
</script>

<style>
#card-welcome{
    background-color: black;
    border-radius: 10px;
    color: white;
    padding: 10px;
}

.card-button{
    border-radius: 10px;
    margin-top: 10px;
}

.card-home-text{
    background-color: purple;
    border-radius: 10px;
    color: white;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #card-welcome{
    margin: 0 auto;
    width: 50%;
  }
}

/* Desktop */
@media screen and (min-width: 860px){
  #card-welcome{
    margin: 0 auto;
    width: 30%;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>
