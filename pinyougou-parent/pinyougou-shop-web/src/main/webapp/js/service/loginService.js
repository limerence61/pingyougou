//服务层
app.service('loginService',function($http){
	    	
	//返回登录的用户名
	this.loginName = function(){
		return $http.get('../login/name.do');
	}
	
});
