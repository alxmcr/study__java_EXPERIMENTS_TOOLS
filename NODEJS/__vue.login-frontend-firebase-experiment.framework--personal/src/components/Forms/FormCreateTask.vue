<template>
    <div id="create-task">
      <form @submit.prevent="createTask" class="todoapp-form create-task-form" method="post">
        <input v-model="task.title" type="text" class="todoapp-input create-task-input" name="task-title-new" id="task-title-new" required />
        <button type="submit" class="todoapp-button todoapp-button-success button-create-task">
          <font-awesome-icon :icon="['fa', 'plus']" />
        </button>
      </form>
    </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'FormCreateTask',
  components: {
    FontAwesomeIcon
  },
  data: function () {
    return {
      task: {
        id: null,
        title: null,
        finished: false
      }
    }
  },
  methods: {
    createTask: function () {
      // Get a reference to the database service
      var database = firebase.database()
      // Tasks
      var tasksRef = database.ref('tasks')

      var max = 2000
      var min = 10
      this.task.id = Math.floor(Math.random() * (max - min) + min)

      console.log('Creating a new task...', JSON.stringify(this.task))

      var idFirebaseTask = 'task_' + this.task.id
      console.log('idFirebaseTask:', idFirebaseTask)
      tasksRef.child(idFirebaseTask).set(this.task)
    }
  }
}
</script>

<style>
#create-task{
  align-items: stretch;
  background-color: black;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 10px 0;
  padding: 10px;
}

.button-create-task{
  margin-top: 15px;
  padding: 10px;
}
</style>
