'use strict';

/**
 * @ngInject
 */
var Config = function($locationProvider, $urlRouterProvider, $stateProvider) {
    $locationProvider.html5Mode({enabled: true, requireBase: false});
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'views/home.html'
        });
};
module.exports = Config;