app.controller('treinoController', function($scope, $rootScope, $location, $http, config){
	
	//TREINO
	
	$scope.treino=$rootScope.treino;
	$scope.treinos=[];
	$scope.tipos=[];
	
	$scope.carregarTipoTreino = function(){
		$http({method: 'GET', url: config.baseUrl +  '/tipo-treino'}).then(function(response){
			$scope.tipos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.carregarTreinos = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/treino'}).then(function(response){
			$scope.treinos = response.data;
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.salvarTreino = function(treino){
		treino.atividades = $scope.atividades;
		$http({method: 'POST', url: config.baseUrl + '/admin/treino', data: treino}).then(function(response){
			$rootScope.treino={};
			$rootScope.atividades=[];
			$scope.carregarTreinos();
			$location.path('/treinos');
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.editarTreino = function(treino){
		$rootScope.treino = treino;
		$rootScope.atividades = treino.atividades;
		$rootScope.somenteLeitura = false;
		$location.path('/cadastrar-treino');
	};
	
	$scope.removerTreino = function(treino){
		$http({method: 'DELETE', url: config.baseUrl + '/admin/treino/' + treino.id}).then(function(response){
			$scope.carregarTreinos();
		}, function(response){
			console.log(response);
		});
	};
	
	$scope.cadastrarTreino = function(){
		$rootScope.treino = {};
		$rootScope.atividades=[];
		$rootScope.somenteLeitura = false;
		$location.path('/cadastrar-treino');
	};
	
	$scope.limparTreino = function(){
		$rootScope.treino = {};
		$rootScope.atividades = [];
		$location.path('/treinos');
	};
	
	$scope.mostrarTempo = function(tempo){
		if(tempo == '' || tempo == null){
			return '';
		} else {
			return tempo;
		};
	};
	
	$scope.mostrarSerieRepeticao = function(serie, repeticao){
		if(serie == null && repeticao == null || serie == '' && repeticao == ''){
			return '';
		} else {
			return serie + ' x ' + repeticao;
		};
	};
	
	$scope.carregarTipoTreino();
	$scope.carregarTreinos();
	
	//ATIVIDADE
	
	$scope.exercicios=[];
	$scope.atividade={};
	$scope.atividades=$rootScope.atividades;
	var isEdit = false;
	var isEditIndex = "";
	
	$scope.carregarExercicios = function(){
		$http({method: 'GET', url: config.baseUrl + '/admin/exercicio'}).then(function(response){
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
		verificaCampoExercicioAtividade();
	};
	
	$scope.detalhesExercicio = function(exercicio){
		$rootScope.exercicio = exercicio;
		$rootScope.somenteLeituraExercicio = true;
		$location.path('/cadastrar-exercicio');
	};
	
	$scope.mostrarDetalhesMusculacao = false;
	$scope.mostrarDetalhesOutros = false;
	
	$scope.limparCampos = function(){
		$scope.atividade.serie = '';
		$scope.atividade.repeticao = '';
		$scope.atividade.tempo = '';
		
		verificaCampoExercicioAtividade();
	};
	
	 function verificaCampoExercicioAtividade(){
		if ($scope.atividade.exercicio == undefined){
			return;
		} else if ($scope.atividade.exercicio.atividadeFisica == 'Musculação'){
			$scope.mostrarDetalhesMusculacao = true;
			$scope.mostrarDetalhesOutros = false;
		} else if ($scope.atividade.exercicio.atividadeFisica == 'Aeróbico' || $scope.atividade.exercicio.atividadeFisica == 'Artes_Marciais'){
			$scope.mostrarDetalhesMusculacao = false;
			$scope.mostrarDetalhesOutros = true;
		};
	};
	
	$scope.carregarExercicios();
});