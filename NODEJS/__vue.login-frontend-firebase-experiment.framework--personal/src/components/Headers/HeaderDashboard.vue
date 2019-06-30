<template>
<header id="header-dashboard" class="todoapp-header">
    <brand-navigation></brand-navigation>
    <actions-navigation-dashboard></actions-navigation-dashboard>
</header>
</template>

<script>
import BrandNavigation from '@/components/Headers/Elements/BrandNavigation'
import ActionsNavigationDashboard from '@/components/Headers/Elements/ActionsNavigationDashboard'

export default {
  name: 'HeaderDashboard',
  components: {
    BrandNavigation,
    ActionsNavigationDashboard
  }
}
</script>

<style>

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #header-dashboard{
    align-items: center;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
}

/* Desktop */
@media screen and (min-width: 860px){

}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>
