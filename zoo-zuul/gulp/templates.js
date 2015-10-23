'use strict';

var gulp = require('gulp');

gulp.task('templates', function() {
    gulp.src('src/main/web/*.html').pipe(gulp.dest('src/main/resources/public'));
    return gulp.src('src/main/web/views/**/*.html')
        .pipe(gulp.dest('src/main/resources/public/views'));
});