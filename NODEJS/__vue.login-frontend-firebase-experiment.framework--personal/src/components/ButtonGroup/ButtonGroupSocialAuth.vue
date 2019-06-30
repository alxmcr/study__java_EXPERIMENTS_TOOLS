<template>
    <div id="button-group-social-auth" class="todoapp-group-button-social social-auth">
      <button @click="connectWithGoogle" type="button" class="todoapp-button todoapp-button-google button-social-auth">Connect with Google</button>
    </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

export default {
  name: 'ButtonGroupSocialAuth',
  data: function () {
    return {
      user: null,
      providerGoogle: new firebase.auth.GoogleAuthProvider(),
      errorAuth: null
    }
  },
  methods: {
    connectWithGoogle: function () {
      console.log('Trying to connect with Google...')

      // View
      let buttonGroupView = this

      // Firebase Auth
      let authFirebase = firebase.auth()

      authFirebase.signInWithPopup(this.providerGoogle).then(function (result) {
        // This gives you a Google Access Token. You can use it to access the Google API.
        var token = result.credential.accessToken
        // The signed-in user info.
        var user = result.user
        // Show Token
        console.log('Token:', token)
        // Show User
        console.log('User logged...', user)
        // Login Success
        buttonGroupView.$router.push({ path: '/welcome' })
      }).catch(function (error) {
        buttonGroupView.errorAuth = error
      })
    }
  }
}
</script>

<style>
#button-group-social-auth{
  margin-top: 25px;
}

.button-social-auth{
  border-radius: 10px;
  margin-bottom: 5px;
  padding: 10px;
}

.button-social-auth:hover{
  background-color: black;
  border: 1px solid black;
  color: white;
}

</style>
