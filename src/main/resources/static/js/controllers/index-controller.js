app.controller('indexController', function($scope, $rootScope, $location){
	$scope.logoff = function(){
		localStorage.clear();
		$rootScope.isLogin = false;
		$location.path('/login');
	};
});