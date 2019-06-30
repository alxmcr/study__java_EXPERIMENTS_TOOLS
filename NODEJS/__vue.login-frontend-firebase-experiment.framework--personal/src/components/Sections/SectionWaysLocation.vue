<template>
  <section id="section-ways-location">
    <div class="ways-location">
      <!-- Location -->
      <div class="location">
          <font-awesome-icon :icon="[ 'fa', 'map-marker-alt' ]" />
          <h2 class="location-title">XYZ Headquarters</h2>
          <div class="location-address">
              <span class="address-text">123 Some Street</span>
              <span class="address-neighborhood">ABC Neighborhood</span>
          </div>
      </div>
    </div>
  </section>
</template>

<script>
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'SectionWaysComunication',
  components: {
      FontAwesomeIcon
  }
}
</script>

<style>
.ways-location{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 0 10px;
}

.location{
  align-items: center;
  background-color: white;
  border: 1px solid white;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 10px 5px;
  padding: 10px;
}

.location-address{
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .ways-location {
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


