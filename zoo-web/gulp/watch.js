'use strict';
var gulp = require('gulp');

gulp.task('watch', function() {
    gulp.watch('src/main/web/js/**/*.js', ['lint']);
    gulp.watch('src/main/web/img/**/*', ['img']);
    gulp.watch('src/main/web/style/**/*.scss',  ['styles']);
    gulp.watch(['src/main/web/*.html', 'src/main/web/views/**/*.html'], ['templates']);
});