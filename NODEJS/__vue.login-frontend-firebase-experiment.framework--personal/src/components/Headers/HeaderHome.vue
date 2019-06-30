<template>
<header id="header-home" class="todoapp-header">
    <brand-navigation></brand-navigation>
    <actions-navigation-home></actions-navigation-home>
</header>
</template>

<script>
import BrandNavigation from '@/components/Headers/Elements/BrandNavigation'
import ActionsNavigationHome from '@/components/Headers/Elements/ActionsNavigationHome'

export default {
  name: 'HeaderHome',
  components: {
    BrandNavigation,
    ActionsNavigationHome
  }
}
</script>

<style>
#header-home{
  background-color: black;
}


/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #header-home{
    align-items: center;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
  }
}

/* Desktop */
@media screen and (min-width: 860px){

}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>
