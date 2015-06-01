(function(app){
	'use strict';

	/**
	 * UV model
	 */
	// TODO Replace with $resource call
	app.factory('emn.uv', function() {
		return function() {
			this.name = '';
			this.nbCours = 0;
		};
	});

	/**
	 * Provider for app menu.
	 */
	app.provider('emn.menu', function () {
		var self = this;

		/**
		 * Defaults values for this service
		 * @type {{items: Array<Object>}}
		 */
		this.defaults = {
			/** Default items in the menu */
			items: [{
				template: 'UVs',
				route: '#/uv'
			}]
		};

		this.$get = ['$rootScope', function($rootScope) {
			// We guaranty self.default.items is Array
			var _items = angular.isArray(self.defaults.items) ? self.defaults.items  : [self.defaults.items];
			return {
				/**
				 * Get a copy of menu items
				 * @returns {Array<Object>} An array of all the items contained by this service
				 */
				get items() {
					return angular.copy(_items);
				},
				/**
				 * Add an item to the menu.
				 * Notify all scopes for the change with menuChanged event
				 * @param item The new item
				 */
				add: function (item) {
					_items.push(item);
					$rootScope.$broadcast('menuChanged');
				}
			};
		}];
	});
}(angular.module('emn-webapp')))