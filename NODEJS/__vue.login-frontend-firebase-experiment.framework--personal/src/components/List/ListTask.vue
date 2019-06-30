<template>
    <div id="list-task">
      <task v-for="task in tasks" :key="task.id" :task="task"></task>
    </div>
</template>

<script>
// Firebase
import firebase from 'firebase'

import Task from '@/components/List/Item/Task'

export default {
  name: 'ListTask',
  components: {
    Task
  },
  data: function () {
    return {
      'tasks': []
    }
  },
  created: function () {
    // Get a reference to the database service
    var database = firebase.database()
    // Tasks
    var tasksRef = database.ref('tasks')

    // Aux variable
    var listTaskView = this

    // Attach an asynchronous callback to read the data at our posts reference
    tasksRef.on('value', function (snapshot) {
      console.info('snapshot:', snapshot.val())
      if (snapshot.val() === null) {
        listTaskView.tasks = []
      } else {
        listTaskView.tasks = snapshot.val()
      }
      console.info('listTaskView.tasks.length:', listTaskView.tasks)
    }, function (errorObject) {
      console.log('The read failed: ' + errorObject.code)
    })
  }
}
</script>

<style>
#list-task{
  border-radius: 10px;
}
</style>
