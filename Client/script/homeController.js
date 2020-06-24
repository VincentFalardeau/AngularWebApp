app.controller('homeController', function($scope, $http) {

    $http.get("http://127.0.0.1:8080/grade/global/all").then(function (response) {

        $scope.globalGrade = response.data;

    });
});