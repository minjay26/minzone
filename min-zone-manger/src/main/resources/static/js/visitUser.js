/**
 * 
 */
var app = angular.module('app', [
	'ngRoute',
	'toggle-switch'
])
.config(["$routeProvider",function($routeProvider){
	$routeProvider
	.when('/personInfo',{
		templateUrl:"../personInfo.html",
		controller: 'InfoCtrl'})
	.when('/user/visit_user/blogs',{template:'这是详细页面'})
	.otherwise({
        redirectTo: '/personInfo'
    });
}]);






























