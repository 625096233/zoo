'use strict';
var gulp        = require('gulp'),
    runSequence = require('run-sequence');

gulp.task('run', ['clean'], function(done) {
    global.production = false;
    done = done || function() {};
    runSequence(['lint',  'styles', 'browserify', 'templates', 'fonts'], 'watch', done)
});