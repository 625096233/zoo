'use strict';
(function() {
    var angular = require('angular');
    angular.element(document).ready(function() {
        angular.module('Zoo.UI', [
            require('angular-ui-router'),
            require('angular-material')])
        .config(require('./config'));
        angular.bootstrap(document, ['Zoo.UI']);
    });
})();