(function(angular) {
	'use strict';

	// Defining application module and its dependencies
	var app = angular.module('emn-webapp', ['ngResource', 'ngRoute', 'ui.bootstrap', 'xeditable']);

	// Defining routes
	app.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {
			templateUrl: 'partials/home.html'
		}).when('/uv', {
			templateUrl: 'partials/uv.html',
			controller: 'UvCtrl'
		}).otherwise({
			redirectTo: '/'
		})
	}]);
}(angular));