//控制层
app.controller('brandController', function($scope, $controller, brandService) {

	/*
	 * 继承basecontroller
	 * $controller是angularjs提供的一个服务,可以实现伪继承;实质上是和baseController共享一个$scope
	 */
	$controller('baseController', {
		$scope : $scope
	});

	$scope.findAll = function() {
		brandService.findAll().success(function(data) {
			$scope.list = data;
		});
	}

	// 分页查询
	$scope.findPage = function(page, size) {
		brandService.findPage(page, size).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;
		});
	}

	/*
	 * //添加 $scope.add = function() { $http.post('../brand/add.do',
	 * $scope.entity).success( function(response) { alert(response.message); if
	 * (response.success) { $scope.reloadList(); } }); }
	 */

	// 点击修改后要实现数据回显,首先要根据id去查询
	$scope.findOne = function(id) {
		brandService.findOne(id).success(function(response) {
			$scope.entity = response;
		});
	}

	/*
	 * //更新 $scope.update = function() { $http.post('../brand/update.do',
	 * $scope.entity).success( function(response) { alert(response.message); if
	 * (response.success) { $scope.reloadList(); } }); }
	 */

	// 合并更新和添加
	$scope.save = function() {
		var object = null;
		if ($scope.entity.id != null) {
			object = brandService.update($scope.entity);
		} else {
			object = brandService.add($scope.entity);
		}

		object.success(function(response) {
			alert(response.message);
			if (response.success) {
				$scope.reloadList();
			}
		});
	}

	// 批量删除
	$scope.del = function() {
		brandService.del($scope.selectedIds).success(function(response) {
			alert(response.message);
			if (response.success) {
				$scope.reloadList();
			}
		});
	}

	// 分页+查询
	$scope.searchEntity = {};
	$scope.search = function(page, size) {
		brandService.search(page, size, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;
				});
	}

});