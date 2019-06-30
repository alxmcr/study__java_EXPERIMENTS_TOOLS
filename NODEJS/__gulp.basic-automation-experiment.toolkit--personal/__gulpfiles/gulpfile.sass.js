'use strict';

var gulp = require("gulp");
var sass = require('gulp-dart-sass');
var sourcemaps = require('gulp-sourcemaps');
var concat = require('gulp-concat');
var minifyCSS = require('gulp-csso');

function styles(){
	return gulp.src("./scss/**/*.scss")
		.pipe(sourcemaps.init())
		.pipe(sass({
			// Options
			outputStyle: 'compressed'
		}).on('error', sass.logError))
		// Source Maps
		.pipe(sourcemaps.write())
		// Concat all the files
		.pipe(concat("mystyles.css"))
		.pipe(minifyCSS({
			// Options
			restructure: false,
			sourceMap: true,
			debug: true
		}))
		.pipe(gulp.dest("./css"));
}

gulp.task("sass", styles);

function watchSass() {
  gulp.watch("./scss/**/*.scss", styles);
}

gulp.task("watch:sass", watchSass);