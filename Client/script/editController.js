app.controller('editController', function($scope, $http) {

    let refreshMarks = function(){

        $http.get("http://127.0.0.1:8080/marks/course?id=" + $scope.course.id).then(function (response) {

            $scope.marks = response.data;
            $scope.selectedMark = $scope.marks[0];

        });
    };

    $scope.refreshMarks = refreshMarks;

    $http.get("http://127.0.0.1:8080/courses/all").then(function (response) {

        $scope.courses = response.data;
        $scope.course = $scope.courses[0];

        refreshMarks();
    });

     $http.get("http://127.0.0.1:8080/categories/all").then(function (response) {

        $scope.categories = response.data;
        $scope.category = $scope.categories[0];

    });

    $scope.edit = function(mark){
        $scope.selectedMark = mark;
    }
});