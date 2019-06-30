<template>
    <footer id="footer-dashboard">
        <element-footer-about></element-footer-about>
        <div class="group-elements-footer">
            <element-footer-social></element-footer-social>
        </div>
    </footer>
</template>

<script>
import ElementFooterAbout from '@/components/Footers/ElementsFooter/ElementFooterAbout'
import ElementFooterSocial from '@/components/Footers/ElementsFooter/ElementFooterSocial'

export default {
  name: 'FooterDashboard',
  components: {
    ElementFooterAbout,
    ElementFooterSocial
  }
}
</script>

<style>
#footer-dashboard{
    background-color: black;
    color: white;
    padding: 10px;
}
</style>
