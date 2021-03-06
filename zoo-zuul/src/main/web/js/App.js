(function() {
    'use strict';
    var angular = require('angular');
    angular.element(document).ready(function() {
        angular.module('Zoo.Authentication', [
            require('angular-ui-router'),
            require('angular-material')])
        .controller('LoginController', require('./controller/LoginController'))
        .factory('LoginService', require('./service/LoginService'))
        .config(require('./Config'));
        angular.bootstrap(document, ['Zoo.Authentication']);
    });
})();