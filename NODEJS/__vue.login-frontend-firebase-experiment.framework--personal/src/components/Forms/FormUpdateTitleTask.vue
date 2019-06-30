<template>
    <div id="update-title-task" v-show="activeForm">
      <form @submit.prevent="saveChanges" class="todoapp-form update-title-form" method="post">
        <input v-model="titleUpdated" type="text" class="update-title-input" name="task-title-updated" id="task-title-updated" required />
        <div class="todoapp-group-button update-group-button">
          <input type="submit" class="todoapp-button todoapp-button-edit button-save" value="Save Changes">
          <input @click="hiddenForm" type="button" class="todoapp-button todoapp-button-danger button-cancel" value="Cancel">
        </div>
      </form>
    </div>
</template>

<script>
export default {
  name: 'FormUpdateTitleTask',
  props: ['active'],
  data: function () {
    return {
      activeForm: this.active,
      titleUpdated: ''
    }
  },
  methods: {
    hiddenForm: function () {
      this.activeForm = !this.activeForm
    },
    saveChanges: function () {
      this.hiddenForm()
      console.log("Saving...", this.titleUpdated);
    }
  }
}
</script>

<style>
</style>
