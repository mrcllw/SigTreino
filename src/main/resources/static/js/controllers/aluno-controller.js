app.controller('alunoController', function($scope, $rootScope, $location, $http, config, toastr, modalService){
	
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
			toastr.success('Aluno cadastrado.');
		}, function(response){
			console.log(response);
			$window.alert(response);
		});
	};
	
	$scope.editarAluno = function(aluno){
		$rootScope.aluno = aluno;
		$location.path('/cadastrar-aluno');
	};
	
	$scope.removerAluno = function(aluno){
		modalService.open('modal-exclusao').result.then(function(response){
			$http({method: 'DELETE', url: config.baseUrl + '/admin/aluno/' + aluno.id}).then(function(response){
				toastr.success('Aluno removido.');
				$scope.carregarAlunos();
			}, function(response){
				console.log(response);
			});
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.cadastrarAluno = function(){
		$rootScope.aluno = {};
		$location.path('/cadastrar-aluno');
	};
	
	$scope.limparAluno = function(){
		$rootScope.aluno = {};
		$location.path('/alunos');
	};
	
	$scope.buscarCep = function(cep){
		if(cep.length == 8){
			$http({method: 'GET', url: config.baseUrl + '/cep/' + cep}).then(function(response){
				if(response.data.erro == true){
					toastr.warning('CEP informado não encontrado.');
				} else {
					$scope.aluno.logradouro=response.data.logradouro;
					if($scope.aluno.complemento == undefined || $scope.aluno.complemento == null){
						$scope.aluno.complemento=response.data.complemento;
					}
					$scope.aluno.estado=response.data.uf;
					$scope.aluno.cidade=response.data.localidade;
					$scope.aluno.bairro=response.data.bairro;
				}
			}, function(response){
				console.log(response);
			});
		}
	};
	
	//---

	$scope.verTreino;
	$scope.treinosAluno=[];
	
	$scope.verTreinoAluno = function(index, aluno){
		$rootScope.aluno = aluno;
		$scope.verTreino=index;
		$scope.carregarTreinosAluno(aluno);
	};
	
	$scope.verCalendarioTreino = function(index){
		return $scope.verTreino === index;
	};
	
	$scope.carregarTreinosAluno = function(aluno){
		$http({method: 'GET', url: config.baseUrl + '/admin/aluno/treino/' + aluno.id}).then(function(response){
			$scope.treinosAluno = response.data;			
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.treinosDia = function(treino, dia){
		for(var i = 0; i < treino.dias.length; i++){
			if(treino.dias[i].toLowerCase() == dia.toLowerCase()){
				return true
			};
		};
	};
	
	$scope.definirAjustar = function(){
		$location.path('/cadastrar-aluno-treino');
	};
	
	//---
	
	$scope.carregarAlunos();	
});