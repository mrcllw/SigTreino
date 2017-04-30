app.controller('alunoController', function($scope, $rootScope, $location, $http, config){
	
	$scope.aluno=$rootScope.aluno;
	$scope.alunos=[];
	
	$scope.carregarAlunos = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/aluno'}).then(function(response){
			$scope.alunos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.salvarAluno = function(aluno){
		$http({method: 'POST', url: config.baseUrl + '/admin/aluno', data:aluno}).then(function(response){
			$rootScope.aluno={};
			$scope.carregarAlunos();
			$location.path('/alunos');
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarAluno = function(aluno){
		$rootScope.aluno = aluno;
		$location.path('/cadastrar-aluno');
	};
	
	$scope.removerAluno = function(aluno){
		$http({method: 'DELETE', url: config.baseUrl + '/admin/aluno/' + aluno.id}).then(function(response){
			$scope.carregarAlunos();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.verTreinoAluno = function(aluno){
		$rootScope.aluno = aluno;
		$location.path('/aluno-treino');
	};
	
	$scope.cadastrarAluno = function(){
		$rootScope.aluno = {};
		$location.path('/cadastrar-aluno');
	};
	
	$scope.limparAluno = function(){
		$rootScope.aluno = {};
		$location.path('/alunos');
	};
	
	$scope.carregarAlunos();	
});