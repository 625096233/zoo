/**
 * @ngInject
 */
var Config = function($locationProvider, $urlRouterProvider, $stateProvider) {
    'use strict';
    $locationProvider.html5Mode({enabled: true, requireBase: false});
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: '/views/login.html',
            controller: 'LoginController',
            controllerAs: 'loginController',
            resolve: {

                /**
                 * @ngInject
                 */
                User: function(LoginService) {
                    return LoginService.loadUser();
                }
            }
        });
};
module.exports = Config;