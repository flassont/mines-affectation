(function(app) {
    'use strict';


    }]);

    /**
     * Controller for UV screen
     */
    app.controller('emn.controller.uvCtrl', ['$scope', '$modal', 'emn.model.uv', function($scope, $modal, Uv) {
        $scope.uvs = [];
        $scope.model = new Uv();

        $scope.open = function() {
            var modalInstance = $modal.open({
                templateUrl: 'uv-add.html',
                controller: 'emn.controller.uvCtrl.modalInstanceCtrl'
            });

            modalInstance.result.then(function(uv) {
                //TODO Save once service will be available
                console.log(JSON.stringify(uv));
            }, function(reason) {
                console.log(reason);
            });
        };
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
    }]);

    /**
     * Controller for the sidebar menu
     */
    app.controller('emn.controller.menuCtrl', ['$scope', '$location', 'emn.service.menu', function($scope, $location, menu) {
        'use strict';

        // Mock for user data
        // TODO Replace user mock with content from service
        var user = {
            lastName: 'DUPONT',
            firstName: 'Paul',
            role: 'Reponsable'
        };
        $scope.user = user;

        // Menu items
        // Update from service when adding menu item
        $scope.menu = {};
        $scope.menu.items = menu.items;
        $scope.$on('menuChanged', onMenuUpdate);

        $scope.isActive = function(link) {
            return '#' + $location.path() === link;
        };

        function onMenuUpdate() {
            $scope.menu.items = menu.items;
        };
    }]);
}(angular.module('emn-webapp')));