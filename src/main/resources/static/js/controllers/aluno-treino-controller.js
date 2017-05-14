app.controller('alunoTreinoController', function($scope, $location, $rootScope, $http, config){
	
	aluno = $rootScope.aluno;
	$scope.treinosCadastro=[];
	$scope.diasTreino=[];
	$scope.diasSelecionados=[];
	$scope.alunoTreino={};
	
	$scope.carregarTreinosAluno = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/aluno/treino/' + aluno.id}).then(function(response){
			$scope.treinosAluno = response.data;			
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarTreinosCadastro = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/treino'}).then(function(response){
			$scope.treinosCadastro = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarDiasTreino = function(){
		$http({method: 'GET', url: config.baseUrl + '/dias-treino'}).then(function(response){
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
		$http({method: 'POST', url: config.baseUrl + '/admin/aluno/treino', data:alunoTreino}).then(function(response){
			$scope.carregarTreinosAluno();
			$scope.alunoTreino={};
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
		$http({method: 'DELETE', url: config.baseUrl + '/admin/aluno/treino/' + treino.id}).then(function(response){
			$scope.carregarTreinosAluno();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.detalhesAlunoTreino = function(treino){
		$rootScope.treino = treino.treino;
		$rootScope.atividades = treino.treino.atividades;
		$location.path('/cadastrar-treino');
		$rootScope.somenteLeitura = true;
	};
	
	$scope.treinosDia = function(treino, dia){
		for(var i = 0; i < treino.dias.length; i++){
			if(treino.dias[i].toLowerCase() == dia.toLowerCase()){
				return true
			};
		};
	};
	
	$scope.carregarTreinosAluno();
	$scope.carregarTreinosCadastro();
	$scope.carregarDiasTreino();
});