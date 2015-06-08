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
			abstract: true,
			template: '<ui-view />'
		}).state('uv.list', {
			url: '',
			templateUrl: 'partials/uv.list.html',
			controller: 'emn.controller.uvCtrl'
		}).state('uv.detail', {
			url: '/{uvId:int}',
			templateUrl: 'partials/uv.detail.html',
			controller: 'emn.controller.uvCtrl.detailCtrl',
			resolve: {
				uv: ['$stateParams', 'emn.model.uv', function($stateParams, uvModel) {
					return uvModel.get($stateParams.uvId);
				}]
			}
		});
	}]);

	// Configuring Restangular's base URL for all REST calls
	app.run(['$location', 'Restangular', function ($location, Restangular) {
		//Extracting root from absolute url
		var baseUrl = $location.absUrl();
		var regex = new RegExp($location.protocol() + ':[/]+[^/]*(/[^#?]*)');
		baseUrl = baseUrl.match(regex)[0] || ''; // get first positive result
		baseUrl = baseUrl + '/rest/';

		Restangular.setBaseUrl(baseUrl);
	}]);
}(angular));