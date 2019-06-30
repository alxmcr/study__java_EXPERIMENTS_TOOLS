<template>
  <div id="banner-about" class="todoapp-banner">
    <div class="banner-content">
      <h1 class="banner-title">About us</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'banner-about'
}
</script>

<style>
#banner-about{
  align-items: center;
  background-image: url(../../../static/banner/banner-about.jpg);
  background-position: center center;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
}

/* Desktop */
@media screen and (min-width: 860px){
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
}
</style>
