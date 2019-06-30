<template>
  <div id="forgot-password">
      <header-home></header-home>
      <div class="forgot-content">
        <form-forgot-password></form-forgot-password>
      </div>
      <footer-home></footer-home>
  </div>
</template>

<script>
import HeaderHome from '@/components/Headers/HeaderHome'
import FormForgotPassword from '@/components/Forms/FormForgotPassword'
import FooterHome from '@/components/Footers/FooterHome'

export default {
  name: 'ForgotPassword',
  components: {
    HeaderHome,
    FormForgotPassword,
    FooterHome
  }
}
</script>

<style>
.forgot-content{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 5px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .forgot-content{
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
