<template>
<!-- TEAM MEMBER -->
<div id="card-team-member" class="team-member">
  <img src="https://image.flaticon.com/icons/svg/702/702023.svg" alt="Member-X" class="team-member-photo">
  <span class="team-member-name">Alejandro M. Coca Rojas</span>
  <div class="team-member-info">
    <span class="team-member-job">Front-End Developer</span>
    <a href="mailto:alxmcr@outlook.com" class="team-member-email">alxmcr@outlook.com</a>
  </div>
  <ul class="team-member-social">
    <li class="team-member-network">
      <a href="https://www.facebook.com" class="team-member-link text-color-facebook">
        <font-awesome-icon :icon="['fab', 'facebook']" />
      </a>
    </li>
    <li class="team-member-network">
      <a href="https://twitter.com" class="team-member-link text-color-twitter">
        <font-awesome-icon :icon="['fab', 'twitter']" />
      </a>
    </li>
  </ul>
</div>
<!-- TEAM MEMBER -->
</template>

<script>
// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'CardTeamMember',
  components: {
    FontAwesomeIcon
  }
}
</script>

<style>
#card-team-member {
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

.team-member-info {
  align-items: center;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  margin: 10px 0;
}

.team-member-link {
  text-decoration: none;
}

.team-member-name {
  background-color: purple;
  color: white;
  border-radius: 10px;
  padding: 10px;
  font-weight: bold;
  text-align: center;
}

.team-member-network {
  border-radius: 10px;
  display: flex;
  margin: 10px;
  padding: 10px;
}

.team-member-photo {
  margin: 10px 0;
  width: 70px;
}

.team-member-social {
  align-items: center;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  list-style-type: none;
  margin: 0;
  padding: 0;
}

/* ---- MEDIA QUERIES ---- */


/* Small Screen */

@media screen and (min-width: 600px) {}


/* Desktop */

@media screen and (min-width: 860px) {}


/* It's over 9000 */

@media screen and (min-width: 1200px) {}
</style>
