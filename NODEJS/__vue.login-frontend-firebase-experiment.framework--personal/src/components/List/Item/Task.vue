<template>
<div id="task">
    <div class="task-info">
        <div v-show="hasError" class="todoapp-form-errors">
          <span class="todoapp-error">{{error.message}}</span>
        </div>
        <div class="todoapp-group-checkbox">
            <input v-model="taskUpdated.finished" type="checkbox" class="update-status-checkbox" id="task-status-updated" name="task-status-updated" />
            <label for="task-status-updated" class="update-status-label">{{this.task.title}}</label>
        </div>
        <div class="todoapp-group-button">
          <button type="button" class="todoapp-button todoapp-button-success button-task" @click="toggleForm">
            <font-awesome-icon icon="arrow-down" />
          </button>
          <button type="button" class="todoapp-button todoapp-button-danger button-task" @click="deleteTask(task.id)">
            <font-awesome-icon icon="times" />
          </button>
        </div>
    </div>
    <div class="update-title-task">
        <form @submit.prevent v-show="activeFormUpdateTitle" class="todoapp-form update-title-form" method="post">
            <input v-model="taskUpdated.title" type="text" class="update-title-input" name="task-title-updated" id="task-title-updated" required />
            <div class="todoapp-group-button">
                <button type="submit" class="todoapp-button todoapp-button-edit button-task" @click="updateTask(task.id)">
                  <font-awesome-icon icon="save" />
                </button>
                <button type="button" class="todoapp-button todoapp-button-danger button-task" @click="toggleForm">
                  <font-awesome-icon icon="arrow-up" />
                </button>
            </div>
        </form>
    </div>
</div>
</template>

<script>
// Firebase
import firebase from 'firebase'

// Font Awesome
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'

export default {
  name: 'Task',
  props: ['task'],
  components: {
    FontAwesomeIcon
  },
  data: function () {
    return {
      activeFormUpdateTitle: false,
      error: {},
      hasError: false,
      taskUpdated: {
        id: this.task.id,
        title: this.task.title,
        finished: this.task.finished
      }
    }
  },
  watch: {
    'taskUpdated.finished': function (newTask, oldTask) {
      this.updateFirebase(this.taskUpdated.id)
    }
  },
  methods: {
    cancelChanges: function () {
      console.log('Getting changes...', JSON.stringify(this.task))
      this.toggleForm()
    },
    deleteTask: function (idTask) {
      console.log('Deleting the task...', JSON.stringify(this.task))
      console.log('idTask...', idTask)
      // Get a reference to the database service
      var database = firebase.database()
      // Tasks
      var tasksRef = database.ref('tasks')
      // Task Child
      var idFirebaseTask = 'task_' + idTask
      console.log('idFirebaseTask:', idFirebaseTask)
      var taskChildDB = tasksRef.child(idFirebaseTask)

      console.log('taskChildDB:', taskChildDB)

      taskChildDB.remove()
    },
    updateTask: function (idTask) {
      if (this.taskUpdated.title) {
        console.log('UpdatedTask...', JSON.stringify(this.taskUpdated))
        this.updateFirebase(idTask)
      } else {
        console.error('The title is empty...')
      }
    },
    updateFirebase: function (idTask) {
      console.log('Updating in Firebase...', JSON.stringify(this.taskUpdated))

      // This view
      let taskView = this
      let taskChildDB = this.getTaskChildDB(idTask)

      taskChildDB.update(this.taskUpdated).then(function () {
        // Hidden Form
        taskView.activeFormUpdateTitle = false
        taskView.hasError = false
      }).catch(function (error) {
        console.error('Task.vue...', error)
        taskView.error = error
        taskView.hasError = true
      })
    },
    toggleForm: function () {
      console.log('toggling...', this.activeFormUpdateTitle)
      this.activeFormUpdateTitle = !this.activeFormUpdateTitle
      console.log('this.activeFormUpdateTitle...', this.activeFormUpdateTitle)
    },
    getTaskChildDB: function (idTask) {
      // Get a reference to the database service
      let database = firebase.database()
      // Tasks
      let tasksRef = database.ref('tasks')
      // Child Task
      let idFirebaseTask = 'task_' + idTask
      console.log('idFirebaseTask:', idFirebaseTask)
      return tasksRef.child(idFirebaseTask)
    }
  }
}
</script>

<style>
#task{
  border-bottom: 1px solid white;
  padding: 10px;
}

.button-task{
  margin-bottom: 10px;
  margin-right: 10px;
}

.update-status-checkbox{
  min-height: 30px;
  min-width: 30px;
}

.update-title-task{
  background-color: purple;
  border-radius: 10px;
  margin-top: 5px;
}

/* ---- MEDIA QUERIES ---- */

/* Small Screen */
@media screen and (min-width: 600px){
  .task-info{
    align-items: center;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
  }
}

/* Desktop */
@media screen and (min-width: 860px){

}

/* It's over 9000 */
@media screen and (min-width: 200px){

}
</style>
