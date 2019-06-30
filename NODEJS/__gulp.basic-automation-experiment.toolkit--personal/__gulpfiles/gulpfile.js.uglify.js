'use strict';

var gulp = require("gulp");
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var pump = require('pump');

// Recommendation for uglify: pump
//
// Mejorar los logs de errores
// $ npm install pump

function compressJS(callB) {
  pump([
      gulp.src("./js/*.js", {
        sourcemaps: true
      }),
      uglify(),
      concat('main.min.js'),
      gulp.dest("./bundle")
    ],
    callB
  );
}

function watchFiles() {
  gulp.watch("./js/*.js", compressJS);
  // gulp.watch(paths.styles.src, styles);
}

gulp.task("watch:js", watchFiles);