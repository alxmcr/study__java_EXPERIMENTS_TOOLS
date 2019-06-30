<template>
  <nav id="actions-navigation-home" class="todoapp-header-nav actions-home">
      <ul class="todoapp-header-list">
          <li class="todoapp-header-subitem option-action">
              <router-link to="/signup" class="link-header link-signup">Sign Up</router-link>
          </li>
      </ul>
  </nav>
</template>

<script>
export default {
  name: 'ActionsNavigationHome'
}
</script>

<style>

#actions-navigation-home{
    display: none;
}

.button-action-user{
    border-radius: 10px;
    padding: 10px;
    margin-right: 5px;
}

.button-action-user:hover{
    background-color: black;
    border: 1px solid black;
    color: white;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  #actions-navigation-home{
    display: flex;
  }
}

/* Desktop */
@media screen and (min-width: 860px){
  #actions-navigation-home{
    display: flex;
  }
}

/* It's over 9000 */
@media screen and (min-width: 1200px){
  #actions-navigation-home{
    display: flex;
  }
}
</style>
