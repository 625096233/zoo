/**
 * @ngInject
 */
var LoginController = function(LoginService, User) {
    'use strict';
    var self = this;
    self.user = User;

    self.login = function() {
        LoginService.login(self.username, self.password).then(function(user) {
            self.user = user;
            delete self.loginFailed;
        }).error(function() {
            self.loginFailed = true;
        });
    };

    return this;
};

module.exports = LoginController;