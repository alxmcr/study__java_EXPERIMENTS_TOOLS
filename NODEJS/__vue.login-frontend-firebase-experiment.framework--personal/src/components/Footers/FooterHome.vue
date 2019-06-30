<template>
    <footer id="footer-home">
        <element-footer-about></element-footer-about>
        <div class="group-elements-footer">
            <element-footer-links-home></element-footer-links-home>
            <element-footer-social></element-footer-social>
        </div>
    </footer>
</template>

<script>
import ElementFooterAbout from '@/components/Footers/ElementsFooter/ElementFooterAbout'
import ElementFooterLinksHome from '@/components/Footers/ElementsFooter/ElementFooterLinksHome'
import ElementFooterSocial from '@/components/Footers/ElementsFooter/ElementFooterSocial'

export default {
  name: 'FooterHome',
  components: {
    ElementFooterAbout,
    ElementFooterLinksHome,
    ElementFooterSocial
  }
}
</script>

<style>
#footer-home{
    align-items: stretch;
    background-color: black;
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
</style>
