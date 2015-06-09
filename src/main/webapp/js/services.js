(function(app){
	'use strict';

	/**
	 * User model
	 */
	app.factory('emn.model.user', ['Restangular', function(Restangular) {
		var user = Restangular.all('user');

		function UserFactory() {
			function UserModel () {
				return {
					id: 0,
					email: '',
					password: '',
					firstName: '',
					lastName: ''
				};
			}
			return new UserModel();
		}

		UserFactory.query = user.getList;
		UserFactory.get = user.get;
		UserFactory.save = user.save;
		UserFactory.delete= user.delete;

		return UserFactory;
	}]);

	/**
	 * UV model
	 */
	// TODO Replace with $resource call
	app.factory('emn.model.uv', ['Restangular', function(Restangular) {
		var user = Restangular.all('uv');

		function UserFactory() {
			function UserModel() {
				this.id = 0;
				this.nom = '';
				this.module = [];
				this.formations = [];
			}
		}
		UserFactory.query = user.getList;
		UserFactory.get = user.get;
		UserFactory.put = user.put;
		UserFactory.post = user.post;
		UserFactory.delete = user.delete;

		return UserFactory;
	}]);

	/**
	 * Module model
	 */
	app.factory('emn.model.module', function() {
		// Creating a factory for REST services
		function ModuleFactory() {
			return new this.model();
		}

		// The Model itself
		function ModuleModel() {
			this.id = null;
			this.nom = '';
			this.dateDebut = undefined;
			this.dateFin = undefined;
			this.enseignements = [];
			this.uv = null;
		}

		ModuleFactory.prototype = {
			model: ModuleModel
		};

		return ModuleFactory;
	});

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
				route: 'uv.list'
				// TODO Add an element for which route to considered and add a directive for comparison
			}, {
				template: 'Utilisateurs',
				route: 'user.list'
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
}(angular.module('emn-webapp')));
