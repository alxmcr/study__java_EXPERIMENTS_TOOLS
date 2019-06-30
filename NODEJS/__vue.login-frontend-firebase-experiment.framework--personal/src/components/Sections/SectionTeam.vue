<template>
  <section id="section-team" class="todoapp-parallax-section">
      <h2 class="todoapp-section-title">Team</h2>
      <div class="team">
        <card-team-member></card-team-member>
        <card-team-member></card-team-member>
        <card-team-member></card-team-member>
        <card-team-member></card-team-member>
        <card-team-member></card-team-member>
      </div>
  </section>
</template>

<script>
import CardTeamMember from '@/components/Cards/CardTeamMember'

export default {
  name: 'SectionTeam',
  components: {
    CardTeamMember
  }
}
</script>

<style>
.team {
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
  .team {
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
