app.service('loginService', function($http) {
	
	//返回登录用户名
	this.loginName = function() {
		return $http.get('../login/name.do');
	}

});