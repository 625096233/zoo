/**
 * @ngInject
 */
var LoginService = function($http, $state) {
    'use strict';
    var self = this;

    self.loadUser = function() {
        $http.get("/security/user").success(function(response) {
            self.user = response.name;
        }).error(function() {
            delete self.user;
            $state.go('login');
        });
    };

    self.login = function(username, password) {
        var data = `username=${username ? encodeURIComponent(username) : ''}&password=${password ? encodeURIComponent(password) : ''}`;
        $http.post('/security/authenticate', data, { headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function(data) {
            return data;
        });
    };

    return this;
};

module.exports = LoginService;