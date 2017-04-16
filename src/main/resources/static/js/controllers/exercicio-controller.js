app.controller('exercicioController', function($scope, $http, config){
	$scope.exercicio = {};
	$scope.exercicios=[];
	$scope.grupamentos = [];
	
	$scope.carregarGruposMuscular = function(){
		$http({method: 'GET', url: config.baseUrl + '/grupo-muscular'}).then(function(response){
			$scope.grupamentos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarExercicios = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/exercicio'}).then(function(response){
			$scope.exercicios = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.salvarExercicio = function(exercicio){
		$http({method: 'POST', url: config.baseUrl + '/admin/exercicio', data: exercicio}).then(function(response){
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
		$http({method: 'DELETE', url: config.baseUrl + '/admin/exercicio/' + exercicio.id}).then(function(response){
			$scope.carregarExercicios();
		}, function(response){
			console.log(response);
		});
	}
	
	$scope.carregarGruposMuscular();
	$scope.carregarExercicios();
	
});