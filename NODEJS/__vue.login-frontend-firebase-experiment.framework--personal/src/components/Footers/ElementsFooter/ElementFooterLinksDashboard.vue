<template>
  <div id="element-footer-links">
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
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ElementFooterLinks'
}
</script>

<style>
#element-footer-links{
  align-self: center;
}

.list-links{
  align-items: center;
}
</style>
