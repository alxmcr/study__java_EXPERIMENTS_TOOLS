<template>
    <div id="home">
      <header-home></header-home>
      <card-home></card-home>
      <div class="home-content">
        <form-sign-in-basic></form-sign-in-basic>
      </div>
      <footer-home></footer-home>
    </div>
</template>

<script>
import ButtonGroupSocialAuth from '@/components/ButtonGroup/ButtonGroupSocialAuth'
import CardHome from '@/components/Cards/CardHome'
import FooterHome from '@/components/Footers/FooterHome'
import FormSignInBasic from '@/components/Forms/FormSignInBasic'
import HeaderHome from '@/components/Headers/HeaderHome'

export default {
  name: 'Home',
  components: {
    ButtonGroupSocialAuth,
    CardHome,
    FooterHome,
    FormSignInBasic,
    HeaderHome
  }
}
</script>

<style>
.home-content{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .home-content{
    align-items: center;
  }
}

/* Desktop */
@media screen and (min-width: 860px){

}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>
