<template>
  <div id="user">
      <card-user></card-user>
  </div>
</template>

<script>
import CardUser from '@/components/Cards/CardUser'

export default {
  name: 'User',
  components: {
    CardUser
  }
}
</script>

<style>
#user{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 40px 10px;
}
</style>
