module.exports = function(grunt) {

    const mozjpeg = require('imagemin-mozjpeg');
  
    grunt.initConfig({
      imagemin: {
        static: {
          options: {
            optimizationLevel: 3,
            svgoPlugins: [{
              removeViewBox: false
            }],
            use: [mozjpeg()] // Example plugin usage
          },
          files: {
            'public/assets/images/kelly-min-unsplash.jpg': 'src/assets/images/kelly-unsplash.jpg',
            'public/assets/images/meckl-min-unsplash.jpg': 'src/assets/images/meckl-unsplash.jpg',
            'public/assets/images/pankaj-min-unsplash.jpg': 'src/assets/images/pankaj-unsplash.jpg'
          }
        },
        dynamic: {
          files: [{
            expand: true,
            cwd: 'src/assets/images/',
            src: ['**/*.{png,jpg,gif}'],
            dest: 'public/assets/images/'
          }]
        }
      }
    });
  
    grunt.loadNpmTasks('grunt-contrib-imagemin');
    grunt.registerTask('default', ['imagemin']);
};