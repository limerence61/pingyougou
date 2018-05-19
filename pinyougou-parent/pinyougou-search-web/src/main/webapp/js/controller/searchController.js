app.controller('searchController',function($scope,$location,searchService){
	
	//定义搜索对象
	$scope.searchMap={'keywords':'','category':'','brand':'',
			'spec':{},'price':'','pageNo':1,'pageSize':40,'sortField':'','sort':''};
	//添加搜索项
	$scope.addSearchItem=function(key,value){
		if('brand'==key||'category'==key||'price'==key){
			$scope.searchMap[key]=value;
		}else{
			$scope.searchMap.spec[key]=value;
		}
		$scope.search();//执行搜索
	}
	//移除搜索项的方法
	$scope.removeSearchItem=function(key){
		if('brand'==key||'category'==key ||'price'==key){
			$scope.searchMap[key]="";
		}else{
			//即要移除key也要移除value
			delete $scope.searchMap.spec[key];
		}
		$scope.search();//执行搜索
	}
	//搜索
	$scope.search = function(){
		//在执行查询前，转换为 int 类型，否则提交到后端有可能变成字符串
		$scope.searchMap.pageNo= parseInt($scope.searchMap.pageNo);
		searchService.search($scope.searchMap).success(
				function(response){
					$scope.resultMap = response;
					//搜索结束后,构建分页标签
					buildPageLabel();
				}
		);
	}
	//构建分页标签的方法
	buildPageLabel = function(){
		$scope.pageLable=[];
		var maxPageNo = $scope.resultMap.totalPages;
		var firstPage = 1;
		var lastPage = maxPageNo;
		$scope.firstDot=true;//前面有点
		$scope.lastDot=true;//后边有点
		if(maxPageNo>5){
			if($scope.searchMap.pageNo<=3){
				lastPage = 5;
				$scope.firstDot=false;//前面没点
			}else if($scope.searchMap.pageNo>=maxPageNo-2){
				firstPage = maxPageNo-4;
				$scope.lastDot=false;//后边没点
			}else{
				firstPage = $scope.searchMap.pageNo-2;
				lastPage = $scope.searchMap.pageNo +2;
			}
		}else{//页码小于5,前后均无点
			$scope.firstDot=false;//前面无点
			$scope.lastDot=false;//后边无点
		}
		//得到起始页和截止页,循环得出分页标签页码
		for(var i=firstPage;i<=lastPage;i++){
			$scope.pageLable.push(i);
		}
	}
	
	//根据页码查询
	$scope.queryByPage=function(pageNo){
		if(pageNo<1||pageNo>$scope.resultMap.totalPages){
			return;
		}else{
			$scope.searchMap.pageNo=pageNo;
			$scope.search();
		}
	}
	
	//判断是否为第一页
	$scope.isTopPage = function(){
		if($scope.searchMap.pageNo==1){
			return true;
		}else{
			return false;
		}
	}
	
	//判断是否最大页
	$scope.isEndPage = function(){
		if($scope.searchMap.pageNo==$scope.resultMap.totalPages){
			return true;
		}else{
			return false;
		}
	}
	
	//判断是否为当前页
	$scope.isCurrentPage = function(pageNo){
		if($scope.searchMap.pageNo==pageNo){
			return true;
		}else{
			return false;
		}
	}
	
	//设置排序查询
	$scope.sortSearch = function(sortField,sort){
		$scope.searchMap.sortField=sortField;
		$scope.searchMap.sort=sort;
		$scope.search();
	}
	
	//如果用户输入的是品牌的关键字，则隐藏品牌列表
	$scope.keywordsIsBrand=function(){
		for(var i=0;i<$scope.resultMap.brandList.length;i++){
			if($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text)>0){
				return true;
			}
		}
		return false;
	}
	
	//接收关键字并进行查询
	$scope.loadkeywords=function(){
		$scope.searchMap.keywords= $location.search()['keywords'];
		$scope.search();
	}
});