/**
 * @ngInject
 */
var LoginController = function(LoginService) {
    'use strict';
    var self = this;

    self.login = function() {
        LoginService.login(self.username, self.password);
    };

    return this;
};

module.exports = LoginController;