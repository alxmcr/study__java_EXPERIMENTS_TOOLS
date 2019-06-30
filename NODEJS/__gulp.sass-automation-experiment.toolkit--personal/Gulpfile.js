var gulp = require("gulp");
var sass = require("gulp-dart-sass");
var concat = require("gulp-concat");
var autoprefixer = require("gulp-autoprefixer");
var imagemin = require("gulp-imagemin");
var browserSync = require("browser-sync").create();

gulp.task("styles", function() {
  return gulp.src("./dev/scss/**/*.scss")
    .pipe(sass({
      outputStyle: 'expanded'
    }))
    .pipe(autoprefixer({
      browsers: ['last 4 versions'],
      cascade: false
    }))
    .pipe(gulp.dest("./production/css"))
    .pipe(browserSync.stream());
});

gulp.task("scripts", function() {
  return gulp.src('./dev/scripts/*.js')
    .pipe(concat("all.js"))
    .pipe(gulp.dest('./production/scripts/'));
});

gulp.task("images", function() {
  return gulp.src('./dev/assets/images/*.jpg')
    .pipe(imagemin())
    .pipe(gulp.dest('./production/assets/images/'));
});

gulp.task('copy', function () {
  return gulp.src('./dev/index.html')
      .pipe(gulp.dest('./production'));
});

gulp.task('init-server', function(){
  browserSync.init({
      server: "./production"
  });
});

gulp.task('watchingFiles', function(){
  gulp.watch('./dev/scss/**/*.scss', gulp.series('styles'));
  gulp.watch('./dev/scripts/*.js', gulp.series('scripts')).on('change', browserSync.reload);
  gulp.watch("./dev/*.html", gulp.series('copy')).on('change', browserSync.reload);
});

gulp.task('default', gulp.parallel('copy', 'styles', 'scripts', 'watchingFiles', 'init-server'));




