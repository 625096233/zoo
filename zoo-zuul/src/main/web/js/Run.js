'use strict';

/**
 * @ngInject
 */
var Run = function(LoginService) {
    LoginService.loadUser();
};
module.exports = Run;