'use strict';

var gulp = require('gulp');

gulp.task('templates', function() {
    gulp.src('src/main/web/*.html').pipe(gulp.dest('src/main/resources/templates'));
    return gulp.src('src/main/web/views/**/*.html').pipe(gulp.dest('src/main/resources/public/templates/views'));
});