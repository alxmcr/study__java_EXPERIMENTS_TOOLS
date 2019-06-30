'use strict';

var gulp = require("gulp");
var sass = require('gulp-dart-sass');
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var minifyCSS = require('gulp-csso');
var autoprefixer = require('gulp-autoprefixer');

function prefixedSass() {
  return gulp.src("./scss/**/*.scss")
    .pipe(sourcemaps.init())
    .pipe(sass({
      // Options
      outputStyle: 'expanded'
    }).on('error', sass.logError))
    // Source Maps
    .pipe(sourcemaps.write())
    // Concat all the files
    .pipe(concat("app.prefixed.css"))
    // Auto-Prefixer
    .pipe(autoprefixer({
      browsers: ['last 2 versions'],
      cascade: false
    }))
    .pipe(gulp.dest("./css"));
}

gulp.task("sass", prefixedSass);

function watchSassPrefixed() {
  gulp.watch("./scss/**/*.scss", prefixedSass);
}

gulp.task("watch:sass-prefix", watchSassPrefixed);