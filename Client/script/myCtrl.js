app.controller('myCtrl', function($scope, $http) {
    // $scope.firstName= "";
    // $scope.lastName= "Doe";
    // $scope.email= "gustav.doe@gmail.com";

    // $scope.firstNames = [
    //     'Jani',
    //     'Carl',
    //     'Margareth',
    //     'Hege',
    //     'Joe',
    //     'Gustav',
    //     'Birgit',
    //     'Mary',
    //     'Kai'
    // ];

    $scope.semesters = [1, 2, 3, 4, 5, 6];
    $scope.semester = $scope.semesters[0];

    $scope.refreshMarks = function(){

        console.log("allo");

        $http.get("http://127.0.0.1:8080/marks?semester=" + $scope.semester).then(function (response) {

            $scope.marks = response.data;

        }, function(response) {

            // Second function handles error
            //$scope.result = "Something went wrong";
        });
    };

    

    $http.get("http://127.0.0.1:8080/grades?semester=1").then(function (response) {

        $scope.grades = response.data;

    }, function(response) {

        // Second function handles error
        //$scope.result = "Something went wrong";
    });

    $http.get("http://127.0.0.1:8080/marks?semester=" + $scope.semester).then(function (response) {

        $scope.marks = response.data;

    }, function(response) {

        // Second function handles error
        //$scope.result = "Something went wrong";
    });




    // $http.get("http://127.0.0.1:8080/courses/all").then(function (response) {

    //     $scope.courses = response.data;

    // }, function(response) {

    //     // Second function handles error
    //     //$scope.result = "Something went wrong";
    // });
});