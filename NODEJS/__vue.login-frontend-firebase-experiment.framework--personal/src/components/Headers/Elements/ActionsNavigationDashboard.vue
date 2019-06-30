<template>
  <nav id="actions-navigation-dashboard" class="todoapp-header-nav actions-dashboard">
      <ul class="todoapp-header-list">
          <li class="todoapp-header-subitem todoapp-img-profile">
              <img :src="switchPhotoURL" class="todoapp-img-user" :alt="switchUsername">
          </li>
          <li class="todoapp-header-subitem">
              <button @click="goProfile" type="button" class="todoapp-button todoapp-button-option">{{switchUsername}}</button>
          </li>
          <li class="todoapp-header-subitem">
              <button @click="logOut" type="button" class="todoapp-button todoapp-button-option">Log Out</button>
          </li>
      </ul>
  </nav>
</template>

<script>
// Firebase
import firebase from 'firebase'

export default {
  name: 'ActionsNavigationDashboard',
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
  computed: {
    // a computed getter
    switchUsername: function () {
      if (this.user.displayName) {
        return this.user.displayName
      }
      return this.user.email
    },

    switchPhotoURL: function () {
      if (this.user.photoURL) {
        return this.user.photoURL
      }
      let photoUserForDefault = '../../../static/profiles/user_profile_generico.png'
      return photoUserForDefault
    }
  },
  methods: {
    logOut: function () {
      let headerDashboard = this
      firebase.auth().signOut().then(function () {
        // Sign-out successful.
        console.log('Sign-out successful.')
      }).catch(function (error) {
        // An error happened.
        headerDashboard.error = error
        console.error(error)
      })
    },
    goProfile: function () {
      const userId = this.user.uid
      this.$router.push({
        path: `/user/${userId}`
      }) // -> /user/123
    }
  }
}
</script>

<style>
#actions-navigation-dashboard{
  padding: 0 10px;
}

.option-signup,
.option-username {
    background-color: black;
    border-radius: 10px;
    padding: 5px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #header-list-brand{
    display: flex;
  }
}

/* Desktop */
@media screen and (min-width: 860px){
  #header-list-brand{
    display: flex;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
  #header-list-brand{
    display: flex;
  }
}
</style>
