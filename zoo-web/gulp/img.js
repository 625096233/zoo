'use strict';

var gulp = require('gulp');

gulp.task('img', function() {
    return gulp.src('src/main/web/img/**/*')
        .pipe(gulp.dest('src/main/resources/public/img'));
});