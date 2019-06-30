<template>
  <nav id="brand-navigation" class="todoapp-header-nav elements-brand">
    <ul class="todoapp-header-list">
      <li class="todoapp-header-subitem todoapp-brand-logo">
        <img src="../../../assets/brand/logoApp.png" alt="To-do App" class="todoapp-img-logo">
      </li>
      <li class="todoapp-header-subitem todoapp-brand-title">
        <h3 class="header-title">To-do App</h3>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'BrandNavigation'
}
</script>

<style>
#brand-navigation{
  background-color: black;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #brand-navigation{
    background-color: transparent;
    color: black;
  }

   .todoapp-brand-title{
     color: black;
     display: flex;
   }
}

/* Desktop */
@media screen and (min-width: 860px){
   .todoapp-brand-title{
     color: black;
     display: flex;
   }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
   .todoapp-brand-title{
     color: black;
     display: flex;
   }
}
</style>
