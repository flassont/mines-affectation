(function(app){
	'use strict';

	/**
	 * UV model
	 */
	// TODO Replace with $resource call
	app.factory('emn.model.uv', function() {
		return function() {
			this.name = '';
		};
	});

	/**
	 * Module model
	 */
	app.factory('emn.model.module', function() {
		return function () {
			this.name = '';
			this.nbIntervenants = 0;
			this.nbGroupes = 0;
			this.nbCours = 0;
			this.nbTD = 0;
		}
	})

	/**
	 * Provider for app menu.
	 */
	app.provider('emn.service.menu', function () {
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
			}, {
				template: 'Modules',
				route: '#/module'
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
