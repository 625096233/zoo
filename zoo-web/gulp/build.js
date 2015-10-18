'use strict';
var gulp        = require('gulp'),
    runSequence = require('run-sequence');

gulp.task('build', ['clean'], function(done) {
    global.production = true;
    done = done || function() {};
    runSequence(['lint',  'styles', 'browserify', 'templates', 'img'], done)
});