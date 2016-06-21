angular.module('telephonyApp.controllers', []).controller('RecordListController', function ($scope, $state, $window, Record) {

    $scope.records = Record.query();

}).controller('RecordViewController', function ($scope, $state, $stateParams, Record) {
    console.log("viewRecord");
    $scope.record = Record.get({ id: $stateParams.id });

    $scope.deleteRecord = function () {
        console.log("deleteRecord");
        $scope.record.$delete(function () {
            $state.go('records');
        });
    };

}).controller('RecordCreateController', function ($scope, $state, $stateParams, Record) {

    console.log("createRecord");
    $scope.record = new Record();

    $scope.addRecord = function () {
        $scope.record.$save(function () {
            $state.go('records');
        });
    }

}).controller('RecordEditController', function ($scope, $state, $stateParams, Record) {

    $scope.updateRecord = function () {
        console.log("updateRecord");
        $scope.record.$update(function () {
            $state.go('records');
        });
    };

    $scope.loadRecord = function () {
        $scope.record = Record.get({ id: $stateParams.id });
    };

    $scope.loadRecord();
});