<template>
  <!-- Way Comunication -->
  <div id="card-way-comunication">
    <font-awesome-icon :icon="way.icon" />
    <h2 class="card-subtitle">{{way.description}}</h2>
    <a :href="way.link" class="card-phone">{{way.info}}</a>
  </div>
  <!-- Way Comunication -->
</template>

<script>
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'CardWayComunication',
  props: ['way'],
  components: {
      FontAwesomeIcon
  }
}
</script>

<style>
#card-way-comunication {
  align-items: center;
  background-color: white;
  border: 1px solid white;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 10px 5px;
  padding: 10px;
}
</style>

