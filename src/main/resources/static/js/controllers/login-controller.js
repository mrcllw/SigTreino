app.controller('loginController', function($scope, $rootScope, $http, $location, config, toastr){
	$scope.academia = {};
	
	$scope.autenticar = function(academia){
		$http.post(config.baseUrl + "/autenticar", academia).then(function(response){
			localStorage.setItem("userToken", response.data.token);
			$rootScope.isLogin = true;
			$location.path('/');
		}, function(response){
			toastr.error(response.data.message);
			$scope.academia={};
		});
	};
	
});