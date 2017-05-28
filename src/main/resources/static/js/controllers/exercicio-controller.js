app.controller('exercicioController', function($scope, $rootScope, $http, $location, config, toastr, modalService){
	$scope.exercicio = $rootScope.exercicio;
	$scope.exercicios=[];
	$scope.grupamentos = [];
	$scope.atividadesFisica = [];
	
	$scope.carregarAtividadesFisica = function(){
		$http({method: 'GET', url: 'http://localhost:6123/atividade-fisica'}).then(function(response){
			$scope.atividadesFisica = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarGruposMuscular = function(){
		$http({method: 'GET', url: 'http://localhost:6123/grupo-muscular'}).then(function(response){
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
			$rootScope.exercicio={};
			$location.path('/exercicios');
			toastr.success('Exercicio cadastrado.');
		}, function(response){
			console.log(response);
			$window.alert(response);
		});
	};
	
	$scope.editarExercicio = function(exercicio){
		$rootScope.exercicio = exercicio;
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.removerExercicio = function(exercicio){
		modalService.open('modal-exclusao').result.then(function(response){
			$http({method: 'DELETE', url: config.baseUrl + '/admin/exercicio/' + exercicio.id}).then(function(response){
				$scope.carregarExercicios();
				toastr.success('Exercicio removido.');
			}, function(response){
				toastr.error(response.data.message);
			});
		}, function(response){
			console.log(response);
		});
		
	};
	
	$scope.cadastrarExercicio = function(){
		$rootScope.exercicio = {};
		$rootScope.somenteLeitura = false;
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.limparExercicio = function(){
		$rootScope.exercicio={};
		$location.path('/exercicios');
	};
	
	$scope.detalhesExercicio = function(exercicio){
		$rootScope.exercicio = exercicio;
		$rootScope.somenteLeituraExercicio = true;
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.grupoMuscularOff = function(){
		var atividadeFisica = document.querySelector('#atividade');
		if(atividadeFisica.value == 'Aeróbico' || atividadeFisica.value == 'Artes_Marciais'){
			$scope.exercicio.grupamento = 'Todos';
			return true;
		} else if($rootScope.somenteLeituraExercicio){
			return true;
		} else {
			return false;
		}
	};
	
	$scope.carregarGruposMuscular();
	$scope.carregarExercicios();
	$scope.carregarAtividadesFisica();
	
});