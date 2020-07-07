var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider

    .when('/', {
        templateUrl : 'pages/home.html',
        controller : 'homeController'
    })
 
    .when('/marks', {
        templateUrl : 'pages/marks.html',
        controller : 'marksController'
    })
 
    .when('/grades', {
        templateUrl : 'pages/grades.html',
        controller : 'gradesController'
    })
 
    .when('/edit', {
        templateUrl : 'pages/edit.html',
        controller : 'editController'
    })
 
    .otherwise({redirectTo: '/'});
});