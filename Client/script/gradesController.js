app.controller('gradesController', function($scope, $http) {

    $scope.semesters = [1,2,3,4,5,6];
    $scope.semester = 1;

    let refreshGrades = function(){

        $http.get("http://127.0.0.1:8080/grades?semester=" + $scope.semester).then(function (response) {

            $scope.grades = response.data;

        });

        $http.get("http://127.0.0.1:8080/grade/global?semester=" + $scope.semester).then(function (response) {

            $scope.globalGrade = response.data;

        });
    };

    $scope.refreshGrades = refreshGrades;

    refreshGrades();
});