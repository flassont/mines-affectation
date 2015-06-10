(function(angular) {
	'use strict';

	// Defining application module and its dependencies
	var app = angular.module('emn-webapp', ['restangular', 'ui.router', 'ui.bootstrap', 'formly', 'formlyBootstrap', 'xeditable']);

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
		}).state('user', {
			url: '/user',
			abstract: true,
			template: '<ui-view />'
		}).state('user.list', {
			url: '',
			templateUrl: 'partials/user.list.html',
			controller: 'emn.controller.userCtrl',
			resolve: {
				users: ['emn.model.user', function(userModel) {
					return userModel.getAll();
				}]
			}
		}).state('wish', {
			url: '/wish',
			templateUrl: 'partials/affectation.html',
			controller: 'emn.controller.affectationCtrl',
			resolve: {
				wishs: ['emn.model.wish', function(WishProvider) {
					return WishProvider.getAll();
				}]
			}
		}).state('wish.module', {
			url: '/{enseignementId:int}',
			templateUrl: 'partials/affectation.user.html',
			controller: 'emn.controller.affectationCtrl.moduleCtrl',
			resolve: {
				enseignement: ['$stateParams', 'emn.model.enseignement', function($stateParams, EnseignementProvider) {
					return EnseignementProvider.get($stateParams.enseignementId);
				}]
			}
		}).state('affectation', {
			url: '/affectations',
			templateUrl: 'partials/affectation.html',
			controller: 'emn.controller.affectationCtrl',
			resolve: {
				wishs: ['emn.model.wish', function(WishProvider) {
					return WishProvider.getAll();
				}]
			}
		}).state('affectation.module', {
			url: '/{enseignementId:int}',
			templateUrl: 'partials/affectation.admin.html',
			controller: 'emn.controller.affectationCtrl.moduleCtrl',
			resolve: {
				enseignement: ['$stateParams', 'emn.model.enseignement', function($stateParams, EnseignementProvider) {
					return EnseignementProvider.get($stateParams.enseignementId);
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

	app.run(['$rootScope', function($rs) {
		$rs.$on('$stateChangeError', console.error.bind(console));
	}]);
}(angular));