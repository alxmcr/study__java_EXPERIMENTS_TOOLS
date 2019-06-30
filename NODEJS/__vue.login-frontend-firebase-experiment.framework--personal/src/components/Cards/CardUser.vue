<template>
    <div id="card-user">
        <img :src="switchPhotoURL" class="card-profile" :alt="switchUsername">
        <h1 class="card-title card-user-title">{{switchUsername}}</h1>
        <div class="card-body card-user-body">
            <span class="card-text card-home-text">{{userProfile.email}}</span>
        </div>
        <div class="card-group-buttons">
            <button @click="goToDashboard" type="button" class="todoapp-button card-button todoapp-button-success">Go to Dashboard</button>
            <button @click="logOut" type="button" class="todoapp-button card-button todoapp-button-danger">Logout</button>
        </div>
    </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

export default {
  name: 'CardUser',
  data: function () {
    return {
      error: {},
      userProfile: {}
    }
  },
  created: function () {
    var cardUserView = this
    firebase.auth().onAuthStateChanged(function (user) {
      if (user) {
        cardUserView.userProfile = user
      } else {
        // No Login
        cardUserView.$router.push({
          path: '/'
        })
      }
    })
  },
  computed: {
    // a computed getter
    switchUsername: function () {
      if (this.userProfile) {
        if (this.userProfile.displayName) {
          return this.userProfile.displayName
        }
      }
      return this.userProfile.email
    },

    switchPhotoURL: function () {
      if (this.userProfile) {
        if (this.userProfile.photoURL) {
          return this.userProfile.photoURL
        }
      }
      let photoUserForDefault = '../../../static/profiles/user_profile_generico.png'
      return photoUserForDefault
    }
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
#card-user{
    align-items: stretch;
    background-color: black;
    border-radius: 10px;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding: 80px 10px;
}

.card-user-body{
  background-color: purple;
}

.card-profile{
  align-self: center;
}
</style>
