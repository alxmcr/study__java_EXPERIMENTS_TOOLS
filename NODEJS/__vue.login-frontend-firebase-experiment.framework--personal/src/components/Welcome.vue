<template>
    <div id="welcome">
      <card-user></card-user>
    </div>
</template>

<script>
import CardUser from '@/components/Cards/CardUser'

export default {
  name: 'Welcome',
  components: {
    CardUser
  }
}
</script>

<style>
#welcome{
  align-items: stretch;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  padding: 10px;
}
</style>
