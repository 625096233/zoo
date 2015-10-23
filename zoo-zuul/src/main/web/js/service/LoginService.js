/**
 * @ngInject
 */
var LoginService = function($http) {
    'use strict';
    var self = this;

    self.login = function(username, password) {
        var headers = username && password ? {authorization : "Basic " + btoa(username + ":" + password)} : {};

        $http.get("/security/user", {headers : headers}).success(function(response) {
            self.authenticated = true;
            self.user = response.name;
        }).error(function() {
            self.authenticated = false;
        });
    };

    return this;
};

module.exports = LoginService;