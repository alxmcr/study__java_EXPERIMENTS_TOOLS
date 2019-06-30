<template>
  <div id="signup-basic">
    <h2 class="todoapp-form-title signup-title">Sign Up</h2>
    <form @submit.prevent="signUpUserWithEmail" class="todoapp-form signup-form" method="post">
      <div class="todoapp-group-input signup-group">
        <label for="email" class="signup-label">Email:</label>
        <input v-model="user.email" type="email" class="signup-input signup-input-text" id="email" name="email" minlength="4" autocomplete="email" required />
      </div>
      <div class="todoapp-group-input signup-group">
        <label for="password" class="signup-label">Password:</label>
        <input v-model="user.password" type="password" class="signup-input signup-input-password" id="password" name="password" minlength="8" autocomplete="password" required />
      </div>
      <button type="submit" class="todoapp-button todoapp-button-success signup-button">Sign Up</button>
    </form>
    <div v-show="hasError" :class="errorForm">
      <span class="todoapp-error">{{error.message}}</span>
    </div>
  </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

export default {
  name: 'FormSignUpBasic',
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
    signUpUserWithEmail: function () {
      let formSignUpBasicView = this
      let authFirebase = firebase.auth()

      if (!this.hasError) {
        authFirebase.createUserWithEmailAndPassword(this.user.email, this.user.password).then(function (user) {
          if (user) {
            // Is there an error? No...
            formSignUpBasicView.hasError = false
            // Show user
            console.log('User created...', JSON.stringify(user))
            // Login Success
            formSignUpBasicView.$router.push({
              path: '/welcome'
            })
          } else {
            // No user is signed up.
            formSignUpBasicView.error = 'No user is signed up'
            formSignUpBasicView.hasError = true
          }
        }).catch(function (error) {
          formSignUpBasicView.handleErrorForm(error)
        })
      }
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
#signup-basic{
  border: 2px solid black;
  border-radius: 10px;
  margin: 40px 10px;
  padding: 10px;
}

.signup-button{
  border-radius: 10px;
  padding: 10px;
}

.signup-button:hover{
  background-color: black;
  border: 1px solid black;
  color: white;
}

.signup-input{
  border: 1px solid white;
  margin-bottom: 10px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #signup-basic{
    width: 65%;
  }
}

/* Desktop */
@media screen and (min-width: 860px){
  #signup-basic{
    width: 50%;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
  #signup-basic{
    width: 30%;
  }
}

</style>
