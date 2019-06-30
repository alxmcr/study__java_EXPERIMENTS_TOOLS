<template>
    <div id="sign-up">
        <header-section></header-section>
        <div class="signup-content">
            <form-sign-up-basic></form-sign-up-basic>
        </div>
        <footer-home></footer-home>
    </div>
</template>

<script>
import FooterHome from '@/components/Footers/FooterHome'
import FormSignUpBasic from '@/components/Forms/FormSignUpBasic'
import HeaderSection from '@/components/Headers/HeaderSection'

export default {
  name: 'SignUp',
  components: {
    FooterHome,
    FormSignUpBasic,
    HeaderSection
  }
}
</script>

<style>
.signup-content{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .signup-content{
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
