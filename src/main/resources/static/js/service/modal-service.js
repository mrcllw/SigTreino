app.service('modalService', function($uibModal){
	this.open = function (template, scope) {
		return $uibModal.open({
			animation: true,
			templateUrl: 'view/' + template + '.html',
			scope : scope,
			backdrop: 'static',
			controller: function ($scope, $uibModalInstance) {
				$scope.ok = function () {
					$uibModalInstance.close();
				};

				$scope.cancel = function () {
					$uibModalInstance.dismiss('cancel');
				};
			}
	    });
	};
});