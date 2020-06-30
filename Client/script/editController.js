app.controller('editController', function($scope, $http) {

    let refreshMarks = function(){

        $http.get("http://127.0.0.1:8080/courses/" + $scope.course.id + "/marks").then(function (response) {

            $scope.marks = response.data;
            $scope.selectedMark = $scope.marks[0];

        });
    };

    function toMarkData(mark){

        return {
            id: mark.id,
            mark: parseFloat(mark.mark),
            idCourse: parseInt($scope.course.id),
            idCategory: parseInt(mark.category.id),
            weight: parseFloat(mark.weight),
            description: mark.description
        };
    }

    $scope.refreshMarks = refreshMarks;

    $http.get("http://127.0.0.1:8080/courses").then(function (response) {

        $scope.courses = response.data;
        $scope.course = $scope.courses[0];

        refreshMarks();
    });

    $http.get("http://127.0.0.1:8080/categories").then(function (response) {

        $scope.categories = response.data;
        $scope.category = $scope.categories[0];

    });

    $scope.edit = function(mark){
        
        $scope.selectedMark = mark;
    }

    $scope.add = function(mark){

        let newMark = toMarkData(mark);
        newMark.id = null;

        $http.post("http://127.0.0.1:8080/marks", newMark).then(function (response) {
            $scope.marks.push(mark);
            $scope.newMark = null;
        });

    }

    $scope.update = function(marks){

        let list = [];
        marks.forEach(mark => list.push(toMarkData(mark)));

        $http.put("http://127.0.0.1:8080/marks", list).then(function (response) {
            console.log("allo");
        });
    }

    $scope.delete = function(mark){

        $http.delete("http://127.0.0.1:8080/marks/" + mark.id).then(function (response) {
            refreshMarks();
        });
    }
});