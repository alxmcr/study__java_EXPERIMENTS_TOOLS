<template>
  <div id="element-footer-social">
    <ul class="todoapp-footer-list list-links-social">
      <li class="todoapp-footer-item">
        <a href="https://www.twitter.com" class="link-footer-social text-color-twitter">
          <font-awesome-icon :icon="['fab', 'twitter']" />
        </a>
      </li>
      <li class="todoapp-footer-item">
        <a href="https://www.facebook.com" class="link-footer-social text-color-facebook">
          <font-awesome-icon :icon="['fab', 'facebook']" />
        </a>
      </li>
      <li class="todoapp-footer-item">
        <a href="https://www.instagram.com" class="link-footer-social text-color-instagram">
          <font-awesome-icon :icon="['fab', 'instagram']" />
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'ElementFooterSocial',
  components: {
    FontAwesomeIcon
  }
}
</script>

<style>
#element-footer-social{
  background-color: white;
  margin-top: 10px;
}

.list-links-social{
  flex-direction: row;
  justify-content: space-around;
}
</style>
