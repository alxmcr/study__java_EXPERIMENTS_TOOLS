<template>
    <div id="signin-basic">
      <h2 class="todoapp-form-title signin-title">Sign In</h2>
      <form @submit.prevent="signInUserWithEmail" class="todoapp-form signin-form" method="post">
        <div class="todoapp-group-input signin-group">
          <label for="email" class="signin-label">Email:</label>
          <input v-model="user.email" type="email" class="signin-input signin-input-text" id="email" name="email" minlength="4" autocomplete="email" required />
        </div>
        <div class="todoapp-group-input signin-group">
          <label for="password" class="signin-label">Password:</label>
          <input v-model="user.password" type="password" class="signin-input signin-input-password" id="password" name="password" minlength="8" autocomplete="password" required />
        </div>
        <router-link to="/forgot" class="link-form link-forgot">Forgot your password?</router-link>
        <button type="submit" class="todoapp-button todoapp-button-success signin-button">Sign In</button>
      </form>
      <div v-show="hasError" :class="errorForm">
        <span class="todoapp-error">{{error.message}}</span>
      </div>
      <hr class="todo-hr">
      <button-group-social-auth></button-group-social-auth>
    </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

import ButtonGroupSocialAuth from '@/components/ButtonGroup/ButtonGroupSocialAuth'

export default {
  name: 'FormSignInBasic',
  components: {
    ButtonGroupSocialAuth
  },
  data: function () {
    return {
      user: {
        email: null,
        password: null
      },
      error: {},
      hasError: false
    }
  },
  computed: {
    errorForm: function () {
      return {
        'todoapp-form-error': this.error
      }
    }
  },
  methods: {
    signInUserWithEmail: function () {
      // This view - auxiliar
      let formSignInView = this

      // User's information
      let email = this.user.email
      let password = this.user.password

      // Firebase Auth
      let authFirebase = firebase.auth()

      authFirebase.signInWithEmailAndPassword(email, password).catch(function (error) {
        formSignInView.handleErrorForm(error)
      })

      authFirebase.onAuthStateChanged(function (user) {
        if (user) {
          // Is there an error? No...
          formSignInView.hasError = false
          // Show user
          console.log('User SignIn...', JSON.stringify(user))
          // Login Success
          formSignInView.$router.push({
            path: '/welcome'
          })
        } else {
          // No user is signed in.
          formSignInView.hasError = true
        }
      })
    },
    handleErrorForm: function (error) {
      this.error = error

      // Is there an error? Yes...
      this.hasError = true
    }
  }
}
</script>

<style>
#signin-basic{
  border: 3px solid black;
  border-radius: 10px;
  color: black;
  margin: 40px 10px;
  padding: 10px;
}

.link-forgot:link,
.link-forgot:visited{
  color: black;
}



.signin-button{
  border-radius: 10px;
  padding: 10px;
}

.signin-button:hover{
  background-color: black;
  border: 1px solid black;
  color: white;
}

.signin-input{
  border: 1px solid white;
  margin-bottom: 10px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #signin-basic{
    width: 65%;
  }
}

/* Desktop */
@media screen and (min-width: 860px){
  #signin-basic{
    width: 50%;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
  #signin-basic{
    width: 30%;
  }
}

</style>
