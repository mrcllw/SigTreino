app.controller('alunoController', function($scope, $rootScope, $http){
	$scope.aluno={};
	$scope.alunos=[];
	
	$scope.carregarAlunos = function(){
		$http({method: 'GET', url: 'http://localhost:6123/aluno'}).then(function(response){
			$scope.alunos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.cadastrarAluno = function(aluno){
		$http({method: 'POST', url: 'http://localhost:6123/aluno', data:aluno}).then(function(response){
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
		$http({method: 'DELETE', url: 'http://localhost:6123/aluno/' + aluno.id}).then(function(response){
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