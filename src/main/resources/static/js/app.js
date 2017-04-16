var app = angular.module('app',['ui.bootstrap', 'ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	.when('/exercicios', {
		templateUrl : 'view/exercicio.html',
		controller : 'exercicioController'
	}).when('/treinos', {
		templateUrl : 'view/treino.html',
		controller : 'treinoController'
	}).when('/alunos', {
		templateUrl : 'view/aluno.html',
		controller : 'alunoController'
	}).when('/aluno-treino', {
		templateUrl : 'view/aluno-treino.html',
		controller : 'alunoTreinoController'
	}).when('/login', {
		templateUrl : 'view/login.html',
		controller : 'loginController'
	}).when('/academia', {
		templateUrl : 'view/academia.html',
		controller : 'academiaController'
	})
	.otherwise({redirectTo : '/'});
});

app.config(function($locationProvider){
	$locationProvider.html5Mode(true);
});

app.config(function($httpProvider){
	$httpProvider.interceptors.push('tokenInterceptor');
});

app.run(function($rootScope){
	$rootScope.aluno = {};
});