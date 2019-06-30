<template>
  <div id="element-footer-links-home">
    <ul class="todoapp-footer-list list-links">
      <li class="todoapp-footer-item">
        <router-link to="/" class="link-footer">Home</router-link>
      </li>
      <li class="todoapp-footer-item">
        <router-link to="/about" class="link-footer">About</router-link>
      </li>
      <li class="todoapp-footer-item">
        <router-link to="/contact" class="link-footer">Contact</router-link>
      </li>
      <li class="todoapp-footer-item">
        <router-link to="/signup" class="link-footer link-signup">Sign Up</router-link>
      </li>
      <li class="todoapp-footer-item">
        <router-link to="/" class="link-footer link-signin">Sign In</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ElementFooterLinksHome'
}
</script>

<style>
#element-footer-links-home{
  align-self: center;
}

.list-links{
  flex-direction: row;
  justify-content: space-around;
}
</style>
