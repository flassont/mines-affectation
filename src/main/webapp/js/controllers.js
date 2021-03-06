(function(app) {
    'use strict';

	app.controller('emn.controller.affectationCtrl', ['$scope', '$state', 'currentState', 'wishs', function($scope, $state, currentState, wishs) {
		$scope.wishs = wishs;

		$scope.go =  function(enseignement) {
			// Go to 'module' child state of state defined as currentState
			// The currentState must be used because reaching '.module'
			// from *.module throws an error (ie the state is not related
			// to the controller)
			$state.go('.module', {
				enseignementId: enseignement.id
			}, {
				relative: currentState
			});
		};
	}]);

	app.controller('emn.controller.affectationCtrl.moduleCtrl', ['$scope', 'Restangular', 'emn.model.wish', 'emn.model.affectation', 'enseignement', function($scope, Restangular, WishProvider, AffectationProvider, enseignement) {
		$scope.enseignement = enseignement;

		/**
		 * Transform the Wish into Affectation
		 * @param wish
		 */
		$scope.acceptWish = function(wish) {
			// Deleting wish is done only once the affectation is succeeded
			var affectation = new AffectationProvider();
			affectation.year = wish.year;
			affectation.nbGroupes = wish.nbGroupes;
			affectation.enseignement = wish.enseignement;
			affectation.intervenant = wish.intervenant;

			AffectationProvider.create(affectation).then(function(newAffectation) {
				enseignement.affectations = enseignement.affectations || [];
				//We assume wish stays the same
				enseignement.affectations.push(newAffectation);
				return WishProvider.select(wish.id).remove();
			}).then(function() {
				removeWish(wish);
			});
		};

		$scope.rejectWish = function(wish) {
			return WishProvider.select(wish.id).remove().then(function()  {
				removeWish(wish);
			});
		};

		$scope.rejectAffectation = function (affectation) {
			AffectationProvider.select(affectation.id).remove().then(function() {
				var index = $scope.enseignement.affectations.indexOf(affectation);
				if(index > -1) {
					$scope.enseignement.affectations.splice(index, 1);
				}
			});
		};

		function removeWish(wish) {
			var index = $scope.wishs.indexOf(wish);
			if(index > -1) {
				$scope.wishs.splice(index, 1);
			}

		}
	}]);

	app.controller('emn.controller.affectationCtrl.userCtrl', ['$scope', 'Restangular','emn.service.auth','emn.model.wish', 'enseignement', function($scope, Restangular, AuthService, WishProvider, enseignement) {
		$scope.enseignement = enseignement;
		console.log(enseignement);
		$scope.subscribing = 0;
		$scope.subscribe = function () {
			$scope.subscribing++;
			var wish = WishProvider();
			wish.nbGroupes = 1;
			wish.year = new Date().getFullYear();
			wish.intervenant = AuthService.user;
			wish.enseignement = enseignement.plain();
			WishProvider.save(wish).then(function() {
				$scope.subscribing--;
			})
		}
	}]);

	/**
	 * Controller for User screen
	 */
	app.controller('emn.controller.userCtrl', ['$scope', '$modal', 'emn.model.user', 'users', function($scope, $modal, UserModel, users) {
		$scope.users = users;
        $scope.createUser = function() {
            var modalInstance = $modal.open({
                templateUrl: 'user-add.html',
                controller: 'emn.controller.userCtrl.createUserCtrl'
            });

            modalInstance.result.then(function (user) {
                return UserModel.save(user).then(function() {
                    $scope.users.push(user);
                });
            }, console.log.bind(console));
        };
	}]);

    /**
     * Controller for UV screen
     */
    app.controller('emn.controller.uvCtrl', ['$scope', '$timeout', '$modal', 'emn.model.uv', function($scope, $timeout, $modal, Uv) {
        $scope.uvs = [];
        $scope.isLoading = 0;
        $timeout(getUv);

        $scope.open = function() {
            var modalInstance = $modal.open({
                templateUrl: 'uv-add.html',
                controller: 'emn.controller.uvCtrl.modalInstanceCtrl'
            });

            modalInstance.result.then(function(uv) {
                Uv.save(uv).then(getUv);
            }, function(reason) {
                console.log(reason);
            });
        };

        function getUv() {
            $scope.isLoading++;
            Uv.getAll().then(function(uvs) {
                $scope.uvs = uvs;
                $scope.isLoading--;
            });
        }
    }]);

    /**
     * Controller for UV modal window
     */
    app.controller('emn.controller.uvCtrl.modalInstanceCtrl', ['$scope', '$modalInstance', 'emn.model.uv', function($scope, $modalInstance, Uv) {
        $scope.model = new Uv();

        $scope.ok = function() {
            $modalInstance.close($scope.model);
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('Cancelled');
        };

        $scope.fields = [{
            key: 'nom',
            type: 'input',
            templateOptions: {
                type: 'text',
                label: 'Intitulé',
                placeholder: 'Intitulé de l\'UV',
                required: true
            }
        }];
    }]);

    /**
     * Controller for UV detail screen
     * UV comes from state's resolve object
     */
    app.controller('emn.controller.uvCtrl.detailCtrl', ['$scope', '$modal', 'uv', function($scope, $modal, uv) {
        setUv(uv);

        $scope.createModule = function() {
            var modalInstance = $modal.open({
                templateUrl: 'create.html',
                controller: 'emn.controller.uvCtrl.detailCtrl.createModuleCtrl'
            });

            modalInstance.result.then(function (module) {
	            module.uv = $scope.uv.id;
                $scope.uv.modules.push(module);
                $scope.uv.save().then(setUv);
            }, console.log.bind(console));
        };

	    function setUv(uv) {
		    $scope.uv = uv;
	    }
    }]);

    app.controller('emn.controller.uvCtrl.detailCtrl.createModuleCtrl', ['$scope', '$modalInstance', 'emn.model.module', function($scope, $modalInstance, moduleModel) {
        $scope.model = new moduleModel();

        $scope.ok = function() {
            $modalInstance.close($scope.model);
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('Cancelled');
        };

        $scope.fields = [{
            key: 'nom',
            type: 'input',
            templateOptions: {
                type: 'text',
                label: 'Intitulé',
                placeholder: 'Intitulé du module',
                required: true
            }
        }];
    }]);

    app.controller('emn.controller.userCtrl.createUserCtrl', ['$scope', '$modalInstance', 'emn.model.user', function($scope, $modalInstance, userModel) {
        $scope.model = new userModel();

        $scope.ok = function() {
            $modalInstance.close($scope.model);
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('Cancelled');
        };

        $scope.fields = [{
            key: 'lastName',
            type: 'input',
            templateOptions: {
                type: 'text',
                label: 'Nom',
                placeholder: 'Nom de l\'utilisateur',
                required: true
            }
        },
            {
                key: 'firstName',
                type: 'input',
                templateOptions: {
                    type: 'text',
                    label: 'Prénom',
                    placeholder: 'Prénom de l\'utilisateur',
                    required: true
                }
            },
            {
                key: 'email',
                type: 'input',
                templateOptions: {
                    type: 'mail',
                    label: 'Mail',
                    placeholder: 'Mail de l\'utilisateur',
                    required: true
                }
            },
            {
                key: 'password',
                type: 'input',
                templateOptions: {
                    type: 'password',
                    label: 'Password',
                    placeholder: 'Mot de passe de l\'utilisateur',
                    required: true
                }
            }];
    }]);

	app.controller('emn.controller.wishCtrl', ['$scope', 'emn.service.auth', 'emn.model.wish', 'uvs', function($scope, AuthService, WishProvider, uvs) {
		$scope.uvs = uvs;

		/**
		 * Add a Enseignement to the wishlist
		 * @param uv            Enseignement's parent
		 * @param module        Enseignement's parent
		 * @param enseignement  Enseignement concerned by the wish
		 * @param nbGroupes     Number of groupes asked
		 */
        $scope.add = function(enseignement, nbGroupes) {
	        if(!!uv || !! module || !!enseignement) {
		        return;
	        }
	        nbGroupes = Number(nbGroupes) || 1;

	        var wish = new WishProvider();
	        wish.intervenant = AuthService.user.id;
	        wish.enseignement = enseignement.id;
	        WishProvider.save(wish);
        }
	}]);

    /**
     * Controller for the sidebar menu
     */
    app.controller('emn.controller.menuCtrl', ['$scope', '$location', 'emn.service.menu', 'emn.service.auth', function($scope, $location, menu, auth) {
        'use strict';

        $scope.user = auth.user;

        // Menu items
        // Update from service when adding menu item
        $scope.menu = {};
        $scope.menu.items = menu.items;
        $scope.$on('menuChanged', onMenuUpdate);

        function onMenuUpdate() {
            $scope.menu.items = menu.items;
        }
    }]);
}(angular.module('emn-webapp')));