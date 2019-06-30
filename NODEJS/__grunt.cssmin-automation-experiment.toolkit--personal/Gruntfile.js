/*global module:false*/
module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
      // Metadata.
      pkg: grunt.file.readJSON('package.json'),
      // Task
      cssmin: {
        options: {
          mergeIntoShorthands: false,
          roundingPrecision: -1
        },
        target: {
          files: {
            'public/css/output.min.css': ['src/css/style001.css', 'src/css/style002.css']
          }
        }
      }
    });
  
    // These plugins provide necessary tasks.
    grunt.loadNpmTasks('grunt-contrib-cssmin');
  
    // Default task.
    grunt.registerTask('default', ['cssmin']);
  
  };