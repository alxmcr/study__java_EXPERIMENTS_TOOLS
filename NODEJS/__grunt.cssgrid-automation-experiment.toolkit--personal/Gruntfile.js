module.exports = function(grunt){
    grunt.initConfig({
        sass: { 
            build: {
                options: {
                    style: 'expanded'
                },
                files: {
                    './production/css/main.css': './dev/scss/**/*.scss'
                }
            }
        },
        uglify: {
            build: {
                options: {
                    sourceMap: true,
                    sourceMapName: './production/scripts/all.sourcemap.map'
                },
                files: {
                    './production/scripts/all.js': './dev/scripts/*.js'
                }
            }
        },
        concat: {
            options: {
              separator: '/* ----  */',
            },
            build: {
              src: ['./dev/scripts/*.js'],
              dest: './production/scripts/all.js',
            },
        },
        imagemin: {
            optimizeImages: {
              files: [{
                expand: true,
                cwd: './dev/assets/images',
                src: ['**/*.{png,jpg,gif}'],
                dest: './production/assets/images/'
              }]
            }
        },
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
        watch: {
            scss: {
              files: ['./dev/scss/**/*.scss'],
              tasks: ['sass:build'],
              options: {
                livereload: true,
              },
            },
            scripts: {
                files: ['./dev/scripts/*.js'],
                tasks: ['concat:build'],
                options: {
                    livereload: true,
                },
            },
        },
        browserSync: {
            dev: {
                bsFiles: {
                    src : [
                        './production/css/*.css',
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

    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-imagemin');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browser-sync');
    
    // Alias tasks
    grunt.registerTask('images', 'imagemin:optimizeImages');
    grunt.registerTask('default', ['copy:html', 'sass:build', 'concat:build', 'uglify:build', 'browserSync', 'watch']);
}