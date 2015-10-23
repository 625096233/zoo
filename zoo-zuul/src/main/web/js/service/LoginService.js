/**
 * @ngInject
 */
var LoginService = function($http, $state, $q) {
    'use strict';
    var self = this;

    self.loadUser = function() {
        var deferred = $q.defer();
        $http.get("/security/user").success(function(response) {
            deferred.resolve(response);
        }).error(function() {
            delete self.user;
            $state.go('login');
            deferred.reject();
        });
        return deferred.promise;
    };

    self.login = function(username, password) {
        var deferred = $q.defer();
        var data = `username=${username ? encodeURIComponent(username) : ''}&password=${password ? encodeURIComponent(password) : ''}`;
        $http.post('/security/authenticate', data, { headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function() {
            self.loadUser().then(function(user) {
                deferred.resolve(user);
            });
        }).error(function() {
            deferred.reject();
        });
        return deferred.promise;
    };

    return this;
};

module.exports = LoginService;