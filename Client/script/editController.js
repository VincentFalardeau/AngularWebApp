app.controller('editController', function($scope, $http) {

    let refreshMarks = function(){

        $http.get("http://127.0.0.1:8080/marks/course?id=" + $scope.course.id).then(function (response) {

            $scope.marks = response.data;
            $scope.selectedMark = $scope.marks[0];

        });
    };

    function toMarkData(mark){

        return {
            id: null,
            mark: parseFloat(mark.mark),
            idCourse: parseInt($scope.course.id),
            idCategory: parseInt(mark.category.id),
            weight: parseFloat(mark.weight),
            description: mark.description
        };
    }

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

    $scope.add = function(mark){

        mark = toMarkData(mark);

        console.log(mark);

        $http.post("http://127.0.0.1:8080/mark/reqBody", mark).then(function(response){
            $scope.marks.push(mark);
            $scope.newMark = null;
        });

        //mark.idCourse = parseInt($scope.course.id);
        //mark.idCategory = parseInt(mark.category.id);
        //mark.mark = parseFloat(mark.mark);
        //mark.weight = parseFloat(mark.weight);

        //console.log(mark);

        //$http.post("http://127.0.0.1:8080/mark", mark).subscribe(mark => $scope.marks.push(mark));

        // $http.post("http://127.0.0.1:8080/mark?mark="+mark.mark+"&description="+mark.description+"&weight="+mark.weight+"&idCourse="+$scope.course.id+"&idCategory="+mark.category.id);//.success(function(response){
        //      //$scope.marks.push(mark);
        // //});
    }
});