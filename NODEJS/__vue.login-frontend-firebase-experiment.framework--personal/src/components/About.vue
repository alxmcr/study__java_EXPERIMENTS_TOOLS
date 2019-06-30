<template>
  <div id="about">
    <header-section></header-section>
    <div class="about-content">
      <banner-about></banner-about>
      <main class="about-sections">
        <section-our-story></section-our-story>
        <section-culture></section-culture>
        <section-team></section-team>
      </main>
    </div>
    <footer-home></footer-home>
  </div>
</template>

<script>
import BannerAbout from '@/components/Banner/BannerAbout'
import FooterHome from '@/components/Footers/FooterHome'
import HeaderSection from '@/components/Headers/HeaderSection'
import SectionCulture from '@/components/Sections/SectionCulture'
import SectionOurStory from '@/components/Sections/SectionOurStory'
import SectionTeam from '@/components/Sections/SectionTeam'

export default {
  name: 'About',
  components: {
    BannerAbout,
    FooterHome,
    HeaderSection,
    SectionCulture,
    SectionOurStory,
    SectionTeam
  }
}
</script>

<style>
</style>
