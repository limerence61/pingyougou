var app = angular.module('pinyougou', []);//不带分页
//定义过滤器
app.filter('trustHtml',['$sce',function($sce){
	return function(data){
		return $sce.trustAsHtml(data);
	}
}]);