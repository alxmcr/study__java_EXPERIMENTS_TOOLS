// Vue dev tools
Vue.config.devtools = true;

Vue.component("person", {
    props: {
        name: {
            type: String,
            required: true
        },
        email: {
            type: String,
            required: true
        },
        profile: {
            type: String,
            default: "https://images.unsplash.com/photo-1517019052566-797fe11415b2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=e5eb66b07b392e09998b2e8fa22dd202&auto=format&fit=crop&w=1051&q=80"
        },
        location: {
            type: String,
            required: true,
            default: "World",
            validator: function(value){
              if (value === "Japon"){
                console.warn("Location must not be Japon...");
                return false;
              }
              // validated
              return true;
            }
        }
    },
    template: "#person-template"
});

new Vue({
    el: "main",
    data: function() {
        return {
            persons: [
              {
                  name: "Alejandro",
                  email: "alejandro@mail.com",
                  profile: "https://randomuser.me/api/portraits/men/83.jpg",
                  location: "Bolivia" 
              },
              {
                  name: "Mauricio",
                  email: "mauricio@mail.com",
                  profile: "https://randomuser.me/api/portraits/men/45.jpg",
                  location: "Japon" 
              }
            ]
        }
    }
});