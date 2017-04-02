app.controller('exercicioController', function($scope, $http){
	$scope.exercicio = {};
	$scope.exercicios=[];
	$scope.grupamentos = [];
	
	$scope.carregarGruposMuscular = function(){
		$http({method: 'GET', url: 'http://localhost:6123/grupo-muscular'}).then(function(response){
			$scope.grupamentos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarExercicios = function(){
		$http({method: 'GET', url: 'http://localhost:6123/exercicio'}).then(function(response){
			$scope.exercicios = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.salvarExercicio = function(exercicio){
		$http({method: 'POST', url: 'http://localhost:6123/exercicio', data: exercicio}).then(function(response){
			$scope.carregarExercicios();
			$scope.exercicio={};
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarExercicio = function(exercicio){
		$scope.exercicio = exercicio;
	};
	
	$scope.removerExercicio = function(exercicio){
		$http({method: 'DELETE', url: 'http://localhost:6123/exercicio/' + exercicio.id}).then(function(response){
			$scope.carregarExercicios();
		}, function(response){
			console.log(response);
		});
	}
	
	$scope.carregarGruposMuscular();
	$scope.carregarExercicios();
	
});