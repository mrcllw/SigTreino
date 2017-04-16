app.controller('alunoController', function($scope, $rootScope, $http, config){
	$scope.aluno={};
	$scope.alunos=[];
	
	$scope.carregarAlunos = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/aluno'}).then(function(response){
			$scope.alunos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.cadastrarAluno = function(aluno){
		$http({method: 'POST', url: config.baseUrl + '/admin/aluno', data:aluno}).then(function(response){
			$scope.aluno={};
			$scope.carregarAlunos();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarAluno = function(aluno){
		$scope.aluno = aluno;
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
	};
	
	$scope.carregarAlunos();	
});