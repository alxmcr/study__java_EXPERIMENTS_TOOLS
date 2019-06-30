<template>
    <div id="sign-in">
        <header-home></header-home>
        <form-sign-in-basic></form-sign-in-basic>
        <footer-home></footer-home>
    </div>
</template>

<script>
import FooterHome from '@/components/Footers/FooterHome'
import FormSignInBasic from '@/components/Forms/FormSignInBasic'
import HeaderHome from '@/components/Headers/HeaderHome'

export default {
  name: 'SignIn',
  components: {
    FooterHome,
    FormSignInBasic,
    HeaderHome
  }
}
</script>

<style>

</style>
