(function(app){
	'use strict';

	/**
	 * User model
	 */
	app.factory('emn.model.user', ['Restangular', function(Restangular) {
		var userResource = Restangular.all('user');

		function UserFactory() {
			return new UserModel();
		}

		function UserModel () {
			this.id = null;
			this.email = '';
			this.password = '';
			this.firstName = '';
			this.lastName = '';
		}

		UserFactory.getAll = userResource.getList;
		UserFactory.get = userResource.get;
		UserFactory.save = userResource.save;
		UserFactory.delete= userResource.delete;

		return UserFactory;
	}]);

	/**
	 * UV model
	 */
	app.factory('emn.model.uv', ['Restangular', function(Restangular) {
		var uvResource = Restangular.all('uv');

		function UvFactory() {
			return new UvModel();
		}

		function UvModel() {
			this.id = null;
			this.nom = '';
			this.modules = [];
			this.formations = [];
		}

		UvFactory.getAll = uvResource.getList;
		UvFactory.get = uvResource.get;
		UvFactory.save = uvResource.save;
		UvFactory.delete = uvResource.remove;

		return UvFactory;
	}]);

	/**
	 * Module model
	 */
	app.factory('emn.model.module', function() {
		// Creating a factory for REST services
		function ModuleFactory() {
			return new ModuleModel();
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

		return ModuleFactory;
	});


	/**
	 * Wish modle
	 */
	app.factory('emn.model.wish', ['Restangular', function(Restangular) {
		var wishResource = Restangular.all('wish');

		function WishFactory() {
			return new WishModel();
		}

		function WishModel() {
			this.id = null;
			this.year = Date.now();
			this.intervenant = null;
			this.enseignement = null;
		}

		WishFactory.getAll = wishResource.getList;
		WishFactory.get = wishResource.get;
		WishFactory.save = wishResource.save;
		WishFactory.delete = wishResource.remove;

		return WishFactory
	}]);

	/**
	 * User service
	 * Give access to login/logout methods and define user's privileges
	 */
	app.factory('emn.service.auth', [function() {
		var user = {
			isAdmin: true,
			email: 'paul.dupont@mines-nantes.fr',
			firstName: 'Paul',
			lastName: 'DUPONT',
			token: '0000000000000000'
		};

		return {
			get user() {
				return user;
			}
		};
	}]);

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
				template: 'Voeux',
				route: 'wish'
			},{
				template: 'Affectations',
				route: 'affectation'
			},{
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
