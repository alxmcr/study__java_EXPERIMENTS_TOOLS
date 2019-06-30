'use strict';

var gulp = require("gulp");
var pug = require('gulp-pug');

function compilePug() {
  return gulp.src('views/*.pug')
    .pipe(pug({
      pretty: '  ',
    })).pipe(gulp.dest("./"));
}

function watchPugFiles() {
  gulp.watch("views/*.pug", compilePug);
}

gulp.task("watch:pug", watchPugFiles);