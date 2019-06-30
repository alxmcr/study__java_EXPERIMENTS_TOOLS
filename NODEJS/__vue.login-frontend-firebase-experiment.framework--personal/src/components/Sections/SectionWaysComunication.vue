<template>
  <section id="section-ways-comunication">
    <div class="ways-comunication">
      <card-way-comunication v-for="way in waysCommunications" :key="way.id" :way="way"></card-way-comunication>
    </div>
  </section>
</template>

<script>
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

import CardWayComunication from '@/components/Cards/CardWayComunication'

export default {
  name: 'SectionWaysComunication',
  components: {
      CardWayComunication,
      FontAwesomeIcon
  },
  data: function () {
    return {
      'waysCommunications': [
          {
            'id': 1,
            'description': 'Phone',
            'link': 'tel:01234567890',
            'info': '01234 567 890',
            'icon': [ 'fa', 'phone' ]
          },
          {
            'id': 2,
            'description': 'Email',
            'link': 'mailto:example@example.org',
            'info': 'example@example.org',
            'icon': [ 'fa', 'at' ]
          },
          {
            'id': 3,
            'description': 'Facebook',
            'link': 'https://www.facebook.com/',
            'info': 'Facebook',
            'icon': [ 'fab', 'facebook' ]
          },
          {
            'id': 4,
            'description': 'Twitter',
            'link': 'https://www.twitter.com/',
            'info': '@twitter',
            'icon': [ 'fab', 'twitter' ]
          },
          {
            'id': 5,
            'description': 'Skype',
            'link': 'callto:01234567890',
            'info': '01234 567 890',
            'icon': [ 'fab', 'skype' ]
          }
        ]
     }
  }
}
</script>

<style>
.ways-comunication{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 0 10px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .ways-comunication {
    flex-direction: row;
    padding: 0;
  }
}

/* Desktop */
@media screen and (min-width: 860px){

}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>


