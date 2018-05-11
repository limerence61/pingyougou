//parent_controller
app.controller('baseController', function($scope) {
	// 分页控件配置
	/*
	 * currentPage:当前页; totalItems:总记录数; itemsPerPage:每页记录数;
	 * perPageOptions:每页记录数的分页选项; onChange:当页码改变自动触发事件.
	 */
	$scope.paginationConf = {
		currentPage : 1,
		totalItems : 10,
		itemsPerPage : 10,
		perPageOptions : [ 10, 20, 30, 40, 50 ],
		onChange : function() {
			$scope.reloadList(); // 重新加载
		}
	};

	// 刷新列表
	$scope.reloadList = function() {
		$scope.search($scope.paginationConf.currentPage,
				$scope.paginationConf.itemsPerPage);
	}

	// 实现复选框更新功能
	$scope.selectIds = [];// 定义勾选的id集合;
	// $event:代表的是<input/>的源,$event.target:代表input这个本身
	$scope.updateSelection = function($event, id) {
		if ($event.target.checked) {
			// 如果选中,添加id到selectIds
			$scope.selectIds.push(id);
		} else {
			// 没选中,移除
			var index = $scope.selectIds.indexOf(id);// 得到id所在索引
			$scope.selectIds.splice(index, 1);// 参数1:要移除的索引;参数2:移除几个.
		}
	}
	
	/*
	 * 提取json字符串中的某个属性,返回一个拼接的字符串</br>
	 * 参数1:json字符串,参数2:需要拼接的属性
	 */
	$scope.jsonToString = function(jsonString,key){
		var json = JSON.parse(jsonString);
		var value = "";
		for(var i=0;i<json.length;i++){
			if(i>0){
				value+=",";
			}
			value+=json[i][key];
		}
		return value;
	}
});
