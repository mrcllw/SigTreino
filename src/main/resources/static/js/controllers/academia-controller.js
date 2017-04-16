app.controller('academiaController', function($scope, $http, config){
	$scope.academia = {};
	
	$scope.cadastrarAcademia = function(academia){
		$http({method: 'POST', url: config.baseUrl + '/academia', data:academia}).then(function(response){
			$scope.academia={};
		}, function(response){
			console.log(response);
		});
	};
});