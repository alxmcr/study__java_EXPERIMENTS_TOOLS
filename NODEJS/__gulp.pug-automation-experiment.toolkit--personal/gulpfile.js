"use strict";

// ========================
var nameCSS = "styles.css";
// ========================

// ========================
var extensionPugTemplate = "*.pug";
var extensionSASS = "*.scss";
var extensionJS = "*.js";

var inputPUG = "./src/pugs/" + extensionPugTemplate;
var inputSASS = "./src/scss/**/" + extensionSASS;
var inputJS = "./src/scripts/" + extensionJS;
// ========================

// ========================
var outputHTML = "./public/";
var outputCSS = "./public/css/";
var outputJS = "./public/js/";
// ========================

var gulp = require("gulp");
var concat = require('gulp-concat');

// SASS
var sass = require('gulp-dart-sass');
var sourcemaps = require('gulp-sourcemaps');
var minifyCSS = require('gulp-csso');

// PUG
const pug = require('gulp-pug');

// BROWSER-SYNC
const browserSync = require('browser-sync').create();

function compilePug() {
  return gulp.src(inputPUG)
    .pipe(pug({
      pretty: '  ',
    })).pipe(gulp.dest(outputHTML));
}

function scripts() {
  console.log("INPUT JS:" + inputJS);
  console.log("OUTPUT JS:" + outputJS);

  return gulp.src(inputJS, {
      sourcemaps: true
    })
    .pipe(concat('main.min.js'))
    .pipe(gulp.dest(outputJS));
}

function styles() {
  console.log("INPUT SASS:" + inputSASS);
  console.log("OUTPUT CSS:" + outputCSS);

  return gulp.src(inputSASS)
    .pipe(sourcemaps.init())
    .pipe(sass({
      // Options
      outputStyle: 'expanded'
    }).on('error', sass.logError))
    // Source Maps
    .pipe(sourcemaps.write())
    // Concat all the files
    .pipe(concat("mystyles.css"))
    .pipe(gulp.dest(outputCSS))
    .pipe(browserSync.stream());
}

function watchSASSFiles() {
  gulp.watch(inputSASS, styles);
}


function watchJSFiles() {
  gulp.watch(inputJS, scripts);
}

function watchPugFiles() {
  gulp.watch(inputPUG, compilePug);
}

gulp.task("watch:sass", watchSASSFiles);
gulp.task("watch:js", watchJSFiles);
gulp.task("watch:pug", watchPugFiles);

gulp.task('default', () => {
  browserSync.init({
    server: './public'
  })

  gulp.watch(inputSASS, styles);
  gulp.watch(inputJS, scripts).on('change', browserSync.reload);
  gulp.watch(inputPUG, compilePug).on('change', browserSync.reload);
})