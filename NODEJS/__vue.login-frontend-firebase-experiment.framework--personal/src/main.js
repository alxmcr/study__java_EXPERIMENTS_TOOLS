// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// Firebase
import firebase from 'firebase'
// Import the Firebase Configuration
import { config } from './_helpers/firebaseConfig'
// Font-Awesome
import fontawesome from '@fortawesome/fontawesome'
import faCheckCircle from '@fortawesome/fontawesome-free-solid/faCheckCircle'
import faArrowDown from '@fortawesome/fontawesome-free-solid/faArrowDown'
import faArrowUp from '@fortawesome/fontawesome-free-solid/faArrowUp'
import faAt from '@fortawesome/fontawesome-free-solid/faAt'
import faEnvelope from '@fortawesome/fontawesome-free-solid/faEnvelope'
import faMapMarkerAlt from '@fortawesome/fontawesome-free-solid/faMapMarkerAlt'
import faPhone from '@fortawesome/fontawesome-free-solid/faPhone'
import faPlus from '@fortawesome/fontawesome-free-solid/faPlus'
import faSave from '@fortawesome/fontawesome-free-solid/faSave'
import faTimes from '@fortawesome/fontawesome-free-solid/faTimes'
import faFacebook from '@fortawesome/fontawesome-free-brands/faFacebook'
import faGoogle from '@fortawesome/fontawesome-free-brands/faGoogle'
import faInstagram from '@fortawesome/fontawesome-free-brands/faInstagram'
import faLinkedin from '@fortawesome/fontawesome-free-brands/faLinkedin'
import faSkype from '@fortawesome/fontawesome-free-brands/faSkype'
import faTwitter from '@fortawesome/fontawesome-free-brands/faTwitter'

// Font-Awesome Configuration
fontawesome.library.add(faArrowDown)
fontawesome.library.add(faArrowUp)
fontawesome.library.add(faAt)
fontawesome.library.add(faCheckCircle)
fontawesome.library.add(faEnvelope)
fontawesome.library.add(faFacebook)
fontawesome.library.add(faMapMarkerAlt)
fontawesome.library.add(faPhone)
fontawesome.library.add(faGoogle)
fontawesome.library.add(faInstagram)
fontawesome.library.add(faLinkedin)
fontawesome.library.add(faPlus)
fontawesome.library.add(faSave)
fontawesome.library.add(faSkype)
fontawesome.library.add(faTimes)
fontawesome.library.add(faTwitter)

console.log('fontawesome.library:', fontawesome.library)

// Initialize Firebase
firebase.initializeApp(config)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
