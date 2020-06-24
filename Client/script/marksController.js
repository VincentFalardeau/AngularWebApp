app.controller('marksController', function($scope, $http) {

    let refreshMarks = function(){

        $http.get("http://127.0.0.1:8080/marks/course?id=" + $scope.course.id).then(function (response) {

            $scope.marks = response.data;

        });

        $http.get("http://127.0.0.1:8080/grade?id=" + $scope.course.id).then(function (response) {

            $scope.grade = response.data;

        });
    };

    $scope.refreshMarks = refreshMarks;

    $http.get("http://127.0.0.1:8080/courses/all").then(function (response) {

        $scope.courses = response.data;
        $scope.course = $scope.courses[0];

        refreshMarks();

    });
});