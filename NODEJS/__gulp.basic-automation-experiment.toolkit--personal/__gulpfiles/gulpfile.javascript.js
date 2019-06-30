'use strict';

var gulp = require("gulp");
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');

function scripts() {
  return gulp.src("./js/*.js", { sourcemaps: true })
    .pipe(concat('main.min.js'))
    .pipe(gulp.dest("./bundle"));
}

function watchFiles() {
  gulp.watch("./js/*.js", scripts);
  // gulp.watch(paths.styles.src, styles);
}

gulp.task("watch", watchFiles);