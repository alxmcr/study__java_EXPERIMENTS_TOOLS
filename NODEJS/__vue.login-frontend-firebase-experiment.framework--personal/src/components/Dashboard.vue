<template>
    <div id="dashboard">
      <header-dashboard></header-dashboard>
      <div class="dashboard-content">
        <h1 class="dashboard-title">Tasks</h1>
        <form-create-task></form-create-task>
        <list-task></list-task>
      </div>
      <footer-dashboard></footer-dashboard>
    </div>
</template>

<script>
import FormCreateTask from '@/components/Forms/FormCreateTask'
import FooterDashboard from '@/components/Footers/FooterDashboard'
import HeaderDashboard from '@/components/Headers/HeaderDashboard'
import ListTask from '@/components/List/ListTask'

export default {
  name: 'Dashboard',
  components: {
    FormCreateTask,
    FooterDashboard,
    HeaderDashboard,
    ListTask
  }
}
</script>

<style>
.dashboard-content{
  padding: 10px;
}

.dashboard-title{
  text-align: center;
}
/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){

}

/* Desktop */
@media screen and (min-width: 860px){
  .dashboard-content{
    margin: 0 auto;
    width: 70%;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){

}
</style>
