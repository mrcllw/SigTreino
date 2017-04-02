app.controller('treinoController', function($scope, $http){
	
	//TREINO
	
	$scope.treino={};
	$scope.treinos=[];
	$scope.tipos=[];
	
	$scope.carregarTipoTreino = function(){
		$http({method: 'GET', url: 'http://localhost:6123/tipo-treino'}).then(function(response){
			$scope.tipos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarTreinos = function(){
		$http({method: 'GET', url: 'http://localhost:6123/treino'}).then(function(response){
			$scope.treinos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.salvarTreino = function(treino){
		treino.atividades = $scope.atividades;
		$http({method: 'POST', url: 'http://localhost:6123/treino', data: treino}).then(function(response){
			$scope.treino={};
			$scope.atividades=[];
			$scope.carregarTreinos();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarTreino = function(treino){
		$scope.treino = treino;
		$scope.atividades = treino.atividades;
	};
	
	$scope.removerTreino = function(treino){
		$http({method: 'DELETE', url: 'http://localhost:6123/treino/' + treino.id}).then(function(response){
			$scope.carregarTreinos();
		}, function(response){
			console.log(response);
		});
	}
	
	$scope.carregarTipoTreino();
	$scope.carregarTreinos();
	
	//ATIVIDADE
	
	$scope.exercicios=[];
	$scope.atividade={};
	$scope.atividades=[];
	var isEdit = false;
	var isEditIndex = "";
	
	$scope.carregarExercicios = function(){
		$http({method: 'GET', url: 'http://localhost:6123/exercicio'}).then(function(response){
			$scope.exercicios = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.adicionarAtividade = function(atividade){
		if(isEdit == false){
			$scope.atividades.push(atividade);
			$scope.atividade={};
		}else{
			$scope.atividades.splice(isEditIndex, 1, atividade);
			isEdit = false;
			$scope.atividade={};
		};
	};
	
	$scope.removerAtividade = function(index){
		$scope.atividades.splice(index, 1);
	};
	
	$scope.editarAtividade = function(atividade, index){
		$scope.atividade = angular.copy(atividade);
		isEdit = true;
		isEditIndex = index;
	};
	
	$scope.carregarExercicios();
});