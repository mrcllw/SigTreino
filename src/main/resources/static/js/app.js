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
	})
	.otherwise({redirectTo : '/'});
});

app.config(function($locationProvider){
	$locationProvider.html5Mode(true);
});