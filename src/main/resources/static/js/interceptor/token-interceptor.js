app.factory('tokenInterceptor', function($q){
	return {
		'request' : function(config){
			config.headers.Authorization = 'Bearer ' + localStorage.getItem('userToken');
			return config;
		},
		'responseError' : function(response){
			if(response.status == 401){
				//redirecionar a pagina de login
			}
			return $q.reject(response);
		}
	}
});