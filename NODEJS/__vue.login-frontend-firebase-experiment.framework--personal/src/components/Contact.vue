<template>
  <div id="contact">
    <header-section></header-section>
    <div class="contact-content">
      <banner-contact></banner-contact>
      <main class="contact-sections">
        <section-ways-comunication></section-ways-comunication>
        <section-ways-location></section-ways-location>
      </main>
    </div>
    <footer-home></footer-home>
  </div>
</template>

<script>
import BannerContact from '@/components/Banner/BannerContact'
import FooterHome from '@/components/Footers/FooterHome'
import HeaderSection from '@/components/Headers/HeaderSection'
import SectionWaysComunication from '@/components/Sections/SectionWaysComunication'
import SectionWaysLocation from '@/components/Sections/SectionWaysLocation'

export default {
  name: 'Contact',
  components: {
    BannerContact,
    FooterHome,
    HeaderSection,
    SectionWaysComunication,
    SectionWaysLocation
  }
}
</script>

<style>
</style>
