app.controller('marksController', function($scope, $http) {

    let refreshMarks = function(){

        $http.get("http://127.0.0.1:8080/courses/" + $scope.course.id + "/marks").then(function (response) {

            $scope.marks = response.data;

        });

        $http.get("http://127.0.0.1:8080/courses/" + $scope.course.id + "/grade").then(function (response) {

            $scope.grade = response.data;

        });
    };

    $scope.refreshMarks = refreshMarks;

    $http.get("http://127.0.0.1:8080/courses").then(function (response) {

        $scope.courses = response.data;
        $scope.course = $scope.courses[0];

        refreshMarks();

    });
});