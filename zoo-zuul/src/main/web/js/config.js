'use strict';

/**
 * @ngInject
 */
var Config = function($locationProvider, $urlRouterProvider, $stateProvider) {
    $locationProvider.html5Mode({enabled: true, requireBase: false});
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: '/views/login.html',
            controller: 'LoginController',
            controllerAs: 'loginController'
        });
};
module.exports = Config;