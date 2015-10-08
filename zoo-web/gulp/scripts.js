'use strict';
var gulp            = require('gulp'),
    gulpIf          = require('gulp-if'),
    babelify        = require('babelify'),
    sourcemaps      = require('gulp-sourcemaps'),
    streamify       = require('gulp-streamify'),
    uglify          = require('gulp-uglify'),
    watchify        = require('watchify'),
    buffer          = require('vinyl-buffer'),
    browserify      = require('browserify'),
    source          = require('vinyl-source-stream');

/*
 Browserifies the given file.
 */
gulp.task('browserify', function() {
    return browserifyFile('app.js');
});

function browserifyFile(file) {
    var browserified = browserify({entries: 'src/main/web/js/app.js', debug: true, cache: {}, packageCache: {}, fullPaths: true}, watchify.args);

    if (!global.production) {
        browserified = watchify(browserified);
        browserified.on('update', bundle);
    }

    [babelify].forEach(function(transform) { browserified.transform(transform) });
    function bundle() {
        var browserifyStream = browserified.bundle();
        return browserifyStream
            .pipe(source(file))
            .pipe(gulpIf(global.production, buffer()))
            .pipe(gulpIf(global.production, sourcemaps.init()))
            .pipe(gulpIf(global.production, streamify(uglify({compress: { drop_console: true }}))))
            .pipe(gulpIf(global.production, sourcemaps.write('./')))
            .pipe(gulp.dest('src/main/resources/static/js/'));
    }
    return bundle();
}