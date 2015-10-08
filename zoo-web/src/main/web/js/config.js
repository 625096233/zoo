'use strict';

/**
 * @ngInject
 */
var Config = function($locationProvider, $urlRouterProvider, $stateProvider) {
    $locationProvider.html5Mode({enabled: true, requireBase: false});
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('index', {
            url: '/',
            templateUrl: 'views/index.html'
        });
};
module.exports = Config;