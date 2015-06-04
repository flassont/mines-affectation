(function(angular) {
	'use strict';

	// Defining application module and its dependencies
	var app = angular.module('emn-webapp', ['restangular', 'ui.router', 'ui.bootstrap', 'xeditable']);

	// Defining routes
	app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
		// Redirect to root if no match
		$urlRouterProvider.otherwise("/");

		// Defining each state for the router
		$stateProvider.state('home', {
			url: '/',
			templateUrl: 'partials/home.html'
		}).state('uv', {
			url: '/uv',
			templateUrl: 'partials/uv.html',
			controller: 'emn.controller.uvCtrl'
		}).state('module', {
			url: '/module',
			templateUrl: 'partials/module.html',
			controller: 'emn.controller.moduleCtrl'
		});
	}]);
}(angular));