'use strict';
var gulp        = require('gulp'),
    sass        = require('gulp-sass'),
    gulpif      = require('gulp-if'),
    sourcemaps  = require('gulp-sourcemaps'),
    minifyCss   = require('gulp-minify-css'),
    streamify   = require('gulp-streamify'),
    concat      = require('gulp-concat'),
    eventStream = require('event-stream');

gulp.task('styles', function () {
    var vendorStyles = gulp.src([
        'node_modules/normalize.css/normalize.css',
        'node_modules/bootstrap/dist/css/bootstrap.css',
        'node_modules/bootstrap/dist/css/bootstrap-theme.css',
        'node_modules/font-awesome/css/font-awesome.css'
    ]);
    var applicationStyles = gulp.src(['src/main/web/style/**/*.scss']).pipe(sass({
            sourceMap: 'sass',
            sourceComments:  'none',
            outputStyle: global.production ? 'compressed' : 'nested'
        }));
    return eventStream.concat(vendorStyles, applicationStyles)
        .pipe(gulpif(global.production, sourcemaps.init()))
        .pipe(gulpif(global.production, streamify(minifyCss)))
        .pipe(gulpif(global.production, sourcemaps.write('./')))
        .pipe(concat('style.css'))
        .pipe(gulp.dest('src/main/resources/static/style'));
});