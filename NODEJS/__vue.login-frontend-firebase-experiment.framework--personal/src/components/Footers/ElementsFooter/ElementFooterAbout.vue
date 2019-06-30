<template>
  <div id="element-footer-about">
    <h3 class="footer-title-app">To-do App</h3>
    <p class="footer-about">
        Todo List is a good app for two reasons. First, you can register your time. Second, you can organize your time.
    </p>
  </div>
</template>

<script>
export default {
  name: 'ElementFooterAbout'
}
</script>

<style>
#element-footer-about{
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-top: 10px;
}

.footer-title-app{
  margin-bottom: 20px;
}
</style>
