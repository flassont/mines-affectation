(function(app) {
    'use strict';

	/**
	 * Controller for User screen
	 */
	app.controller('emn.controller.userCtrl', ['$scope', '$modal', 'emn.model.user', 'users', function($scope, $modal, UserModel, users) {
		$scope.users = users;
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

	app.controller('emn.controller.wishCtrl', ['$scope', 'uvs', function($scope, uvs) {
		$scope.uvs = uvs;
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