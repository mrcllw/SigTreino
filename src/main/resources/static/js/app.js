var app = angular.module('app',['ui.bootstrap', 'ngRoute', 'ngAnimate', 'ngMask', 'toastr']);

app.config(function($routeProvider){
	$routeProvider
	.when('/', {
		templateUrl : 'view/inicio.html'
	}).when('/exercicios', {
		templateUrl : 'view/lista-exercicios.html',
		controller : 'exercicioController'
	}).when('/cadastrar-exercicio', {
		templateUrl : 'view/cadastrar-exercicio.html',
		controller : 'exercicioController'
	}).when('/treinos', {
		templateUrl : 'view/lista-treinos.html',
		controller : 'treinoController'
	}).when('/cadastrar-treino', {
		templateUrl : 'view/cadastrar-treino.html',
		controller : 'treinoController'
	}).when('/alunos', {
		templateUrl : 'view/lista-alunos.html',
		controller : 'alunoController'
	}).when('/cadastrar-aluno', {
		templateUrl : 'view/cadastrar-aluno.html',
		controller : 'alunoController'
	}).when('/cadastrar-aluno-treino', {
		templateUrl : 'view/cadastrar-aluno-treino.html',
		controller : 'alunoTreinoController'
	}).when('/login', {
		templateUrl : 'view/login.html',
		controller : 'loginController'
	}).when('/academia', {
		templateUrl : 'view/cadastrar-academia.html',
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

app.run(function($rootScope, $location){
	$rootScope.aluno = {};
	$rootScope.exercicio = {};
	$rootScope.treino = {};
	$rootScope.atividades = [];
	$rootScope.isLogin = false;
	$rootScope.somenteLeitura=false;
	$rootScope.somenteLeituraExercicio=false;
	
	var history = [];
	
	$rootScope.$on('$routeChangeSuccess', function() {
        history.push($location.$$path);
    });

    $rootScope.voltar = function () {
        var prevUrl = history.length > 1 ? history.splice(-2)[0] : "/";
        $rootScope.somenteLeituraExercicio = false;
        $location.path(prevUrl);
    };
});