app.controller('exercicioController', function($scope, $rootScope, $http, $location, config){
	$scope.exercicio = $rootScope.exercicio;
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
			$location.path('/exercicios');
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarExercicio = function(exercicio){
		$scope.exercicio = exercicio;
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.removerExercicio = function(exercicio){
		$http({method: 'DELETE', url: config.baseUrl + '/admin/exercicio/' + exercicio.id}).then(function(response){
			$scope.carregarExercicios();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.cadastrarExercicio = function(){
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.limparExercicio = function(){
		$rootScope.exercicio={};
		$location.path('/exercicios');
	};
	
	$scope.carregarGruposMuscular();
	$scope.carregarExercicios();
	
});