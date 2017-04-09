app.controller('alunoTreinoController', function($scope, $rootScope, $http){
	
	aluno = $rootScope.aluno;
	$scope.treinosAluno=[];
	$scope.treinosCadastro=[];
	$scope.diasTreino=[];
	$scope.diasSelecionados=[];
	$scope.alunoTreino={};

	$scope.carregarTreinosAluno = function(){
		$http({method: 'GET', url: 'http://localhost:6123/aluno/treino/' + aluno.id}).then(function(response){
			$scope.treinosAluno = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarTreinosCadastro = function(){
		$http({method: 'GET', url: 'http://localhost:6123/treino'}).then(function(response){
			$scope.treinosCadastro = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarDiasTreino = function(){
		$http({method: 'GET', url: 'http://localhost:6123/dias-treino'}).then(function(response){
			$scope.diasTreino = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.diaChecked = function(dia){
		var idx = $scope.diasSelecionados.indexOf(dia);
		
		if(idx > -1){
			$scope.diasSelecionados.splice(idx,1);
		}else{
			$scope.diasSelecionados.push(dia);
		};
	};
	
	$scope.cadastrarTreino = function(alunoTreino){
		alunoTreino.aluno = aluno;
		alunoTreino.dias = $scope.diasSelecionados;
		$http({method: 'POST', url: 'http://localhost:6123/aluno/treino', data:alunoTreino}).then(function(response){
			$scope.carregarTreinosAluno();
			$scope.alunoTreino.treino = {};
			$scope.diasSelecionados = [];
		}, function(response){
			console.log(response.data.message);
		});
	};
	
	$scope.editarTreino = function(treino){
		$scope.alunoTreino.id = treino.id;
		$scope.alunoTreino.treino = treino.treino;
		$scope.diasSelecionados = treino.dias;
	};
	
	$scope.removerTreino = function(treino){
		$http({method: 'DELETE', url: 'http://localhost:6123/aluno/treino/' + treino.id}).then(function(response){
			$scope.carregarTreinosAluno();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarTreinosAluno();
	$scope.carregarTreinosCadastro();
	$scope.carregarDiasTreino();
});