/*global module:false*/
module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        browserify: {
            build: {
                src: './src/scripts/array.js',
                dest: './dist/bundle.js'
            }
        }
    });
  
    // These plugins provide necessary tasks.
    grunt.loadNpmTasks('grunt-browserify');
  
    // Default task.
    grunt.registerTask('default', ['browserify']);
  
  };