app.controller('academiaController', function($scope, $http, config, $location, toastr){
	$scope.academia = {};
	$scope.tipo = 'fisica';
	
	$scope.cadastrarAcademia = function(academia){
		$http({method: 'POST', url: config.baseUrl + '/academia', data:academia}).then(function(response){
			$scope.academia={};
			$location.path('/login');
			toastr.success('Academia cadastrada.');
		}, function(response){
			if(response.status == 302){
				toastr.error(response.data.message);
				$scope.academia = {};
			}
			console.log(response);
		});
	};
	
	$scope.limparAcademia = function(){
		$scope.academia = {};
	};
	
	$scope.cancelarCadastro = function(){
		$scope.limparAcademia();
		$location.path('/');
	};
	
	$scope.buscarCep = function(cep){
		if(cep.length == 8){
			$http({method: 'GET', url: config.baseUrl + '/cep/' + cep}).then(function(response){
				if(response.data.erro == true){
					toastr.warning('CEP informado não encontrado.');
				} else {
					$scope.academia.logradouro=response.data.logradouro;
					if($scope.academia.complemento == undefined || $scope.academia.complemento == null){
						$scope.academia.complemento=response.data.complemento;
					}
					$scope.academia.estado=response.data.uf;
					$scope.academia.cidade=response.data.localidade;
					$scope.academia.bairro=response.data.bairro;
				}
			}, function(response){
				console.log(response);
			});
		};
	};
	
	$scope.buscarCpf = function(cpf){
		if(cpf != undefined && cpf.length == 11){
			$http({method:'GET', url: config.baseUrl + '/cpf/' + cpf}).then(function(response){
				console.log(response.data);
				console.log(response.status);
			}, function(response){
				toastr.warning(response.data.message);
			});
		};
	};
	
	$scope.buscarCnpj = function(cnpj){
		if(cnpj != undefined && cnpj.length == 14){
			$http({method:'GET', url: config.baseUrl + '/cnpj/' + cnpj}).then(function(response){
				if(response.data.status == 'ERROR'){
					toastr.warning(response.data.message);
				} else {
					$scope.academia.dataAberturaNascimento=response.data.abertura;
					$scope.academia.razaoSocialNomeEmpresario=response.data.nome;
					$scope.academia.nomeFantasiaEmpresarial=response.data.fantasia;
					$scope.academia.email=response.data.email;
					$scope.academia.telefone=response.data.telefone;
					$scope.academia.cep=response.data.cep.replace('.','').replace('-', '');
					$scope.academia.numero=response.data.numero;
					$scope.academia.logradouro=response.data.logradouro;
					$scope.academia.bairro=response.data.bairro;
					$scope.academia.complemento=response.data.complemento;
					$scope.academia.cidade=response.data.municipio;
					$scope.academia.estado=response.data.uf;
				}
			}, function(response){
				if(response.data.status == 500){
					toastr.warning(response.data.message);
				}
				console.log(response.data);
				console.log(response.status);
			});
		};
	};
});