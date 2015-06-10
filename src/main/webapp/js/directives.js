/**
 * @file AngularJS directives.
 * A directive is a reusable logic component,
 * and can be applied to element in different ways :
 * - Custom HTML element ('E' restriction)
 * - Class ('C' restriction)
 * - data-* HTML5 attributes ('A' restriction)
 */

(function(app) {
	"use strict";

	app.directive('emnUvList', function() {
		return {
			restrict: 'E',
			scope: {
				onEnseignementSelect: '&emnEnseignementSelect'
			}, // The directive will use an isolated scope
			templateUrl: 'partials/directives/uv.list.html',
			controller: ['$scope','emn.model.uv', function($scope, UvProvider) {
				UvProvider.getAll().then(function(uvs) {
					$scope.uvs = uvs;
				});
			}]
		};
	});

})(angular.module('emn-webapp'));