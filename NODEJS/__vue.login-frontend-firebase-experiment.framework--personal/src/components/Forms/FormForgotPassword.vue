<template>
  <div id="form-forgot-password">
      <h2 class="todoapp-form-title forgot-title">Account Recovery</h2>
      <p class="todoapp-message">Forgot your account's password? Enter your email address and we'll send you a recovery link.</p>
      <form @submit.prevent="accountRecovery" class="todoapp-form" method="post">
        <div class="todoapp-group-input forgot-group">
          <input v-model="emailAddress" type="email" class="forgot-input forgot-input-email" id="email" name="email" minlength="4" autocomplete="email" placeholder="example@example.org" required />
        </div>
        <button type="submit" class="todoapp-button todoapp-button-success recovery-button">Send Recovery Email</button>
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
  name: 'FormForgotPassword',
  data: function () {
    return {
      emailAddress: null,
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
    accountRecovery: function () {
      let auth = firebase.auth()
      let formForgotPasswordView = this

      auth.sendPasswordResetEmail(this.emailAddress).then(function () {
        // Email sent.
        formForgotPasswordView.hasError = false
        alert('Email sent.')
        // Login Success
        formForgotPasswordView.$router.push({
          path: '/'
        })
      }).catch(function (error) {
        // An error happened.
        formForgotPasswordView.handleErrorForm(error)
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
#form-forgot-password{
    background-color: purple;
    border-radius: 10px;
    color: white;
    margin: 40px 10px;
    padding: 10px;
}

.recovery-button{
  border-radius: 10px;
  padding: 10px;
  margin-top: 10px;
}
</style>
