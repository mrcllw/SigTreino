app.controller('indexController', function($scope, $rootScope, $location){
	$scope.menuRedux = true;
	
	$scope.logoff = function(){
		localStorage.clear();
		$rootScope.isLogin = false;
		$location.path('/login');
	};
});