<template>
<header id="header-section" class="todoapp-header">
    <brand-navigation></brand-navigation>
    <actions-navigation-section></actions-navigation-section>
</header>
</template>

<script>
import BrandNavigation from '@/components/Headers/Elements/BrandNavigation'
import ActionsNavigationSection from '@/components/Headers/Elements/ActionsNavigationSection'

export default {
  name: 'HeaderSection',
  components: {
    BrandNavigation,
    ActionsNavigationSection
  }
}
</script>

<style>
#header-section{
    background-color: black;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #header-section{
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
