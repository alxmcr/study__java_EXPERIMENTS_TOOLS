module.exports = function(grunt) {

    grunt.initConfig({
        copy: {
            html: {                
                files: [
                    { 
                        expand: true,
                        cwd: './dev',
                        src: 'index.html', 
                        dest: './production/', 
                        filter: 'isFile'
                    }
                ]
            }
        },
        sass: {
            compile: {
            files: {
                './production/styles/main.css': './dev/scss/**/*.scss',
            }
            }
        },
        concat: {
            options: {
            separator: '/* -- Fin -- */',
            },
            dist: {
            src: ['./dev/scripts/*.js'],
            dest: './production/scripts/all.js',
            },
        },
        watch: {
            styles: {
                files: './dev/scss/**/*.scss',
                tasks: ['sass'],
                options: {
                    livereload: true,
                },
            },
            scripts: {
                files: ['./dev/scripts/*.js'],
                tasks: ['concat', 'browserify'],
                options: {
                    livereload: true,
                },
            },
        },
        browserify: {
            build: {
            src: './production/scripts/all.js',
            dest: './production/scripts/bundle.js'
            }
        },
        browserSync: {
            dev: {
                bsFiles: {
                    src : [
                        './production/styles/*.css',
                        './production/scripts/*.js',
                        './production/*.html'
                    ]
                },
                options: {
                    watchTask: true,
                    server: './production'
                }
            }
        }
    });
  
    grunt.loadNpmTasks("grunt-browser-sync");
    grunt.loadNpmTasks("grunt-browserify");
    grunt.loadNpmTasks("grunt-contrib-concat");
    grunt.loadNpmTasks("grunt-contrib-copy");
    grunt.loadNpmTasks("grunt-contrib-sass");
    grunt.loadNpmTasks("grunt-contrib-watch");

    grunt.registerTask('default', ['copy', 'sass', 'concat', 'browserify', 'browserSync', 'watch']);
  };