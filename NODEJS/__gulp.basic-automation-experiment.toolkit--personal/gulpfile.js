'use strict';

const gulp = require('gulp');

// PUG
const pug = require('gulp-pug');

// SASS
var sass = require('gulp-dart-sass');
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var minifyCSS = require('gulp-csso');
var autoprefixer = require('gulp-autoprefixer');

// JS
const babel = require('gulp-babel');

const browserSync = require('browser-sync').create();

var initPathServer ="./public";

var inputPug = "./src/views/*.pug";
var inputSCSS = "./src/scss/**/*.scss";
var inputJS = "./src/scripts/*.js";

var outputPug = initPathServer + "/";
var outputCSS = initPathServer + "/css";
var outputJS = initPathServer + "/js";

function prefixedSass() {
  return gulp.src(inputSCSS)
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
    .pipe(gulp.dest(outputCSS))
    .pipe(browserSync.stream());
}

function scripts() {
  return gulp.src(inputJS, { sourcemaps: true })
    .pipe(concat('main.min.js'))
    .pipe(gulp.dest(outputJS));
}

function compilePug() {
  return gulp.src(inputPug)
    .pipe(pug({
      pretty: '  ',
    })).pipe(gulp.dest(outputPug));
}

function watchFiles(){
  gulp.watch(inputSCSS, prefixedSass);
  gulp.watch(inputPug, compilePug);
  gulp.watch(inputJS, scripts);
}

// gulp.task('default', watchFiles);

gulp.task('default', ()=>{
    browserSync.init({
        server: './public'
    })

    gulp.watch(inputSCSS, prefixedSass);
    gulp.watch(inputPug, compilePug).on('change', browserSync.reload)
    gulp.watch(inputJS, scripts).on('change', browserSync.reload)
})