<template>
    <div class="todoapp-group-button button-group-actions-welcome">
      <input @click="logOut" type="button" value="Logout" class="button-action button-action-logout">
      <input @click="goToDashboard" type="button" value="Continue" class="button-action button-action-continue">
    </div>
</template>

<script>
export default {
  name: 'ButtonGroupActionsWelcome',
  methods: {
    goToDashboard: function () {
      let myPath = '/dashboard'
      console.log('Trying to go ...', myPath)
      this.$router.push({ path: myPath })
    },
    logOut: function () {
      console.log('Trying to log out ...')
    }
  }
}
</script>

<style>
</style>
