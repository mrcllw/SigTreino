app.controller('loginController', function($scope, $rootScope, $http, $location, config){
	
	$scope.autenticar = function(academia){
		$http.post(config.baseUrl + "/autenticar", academia).then(function(response){
			localStorage.setItem("userToken", response.data.token);
			$rootScope.isLogin = true;
			$location.path('/');
		}, function(response){
			console.log(response);
		});
	};
	
});