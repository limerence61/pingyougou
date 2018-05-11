//控制层 
app.controller('goodsController', function($scope, $controller,$location,goodsService,
		uploadService, itemCatService, typeTemplateService) {

	$controller('baseController', {
		$scope : $scope
	});// 继承

	// 读取列表数据绑定到表单中
	$scope.findAll = function() {
		goodsService.findAll().success(function(response) {
			$scope.list = response;
		});
	}

	// 分页
	$scope.findPage = function(page, rows) {
		goodsService.findPage(page, rows).success(function(response) {
			$scope.list = response.rows;
			$scope.paginationConf.totalItems = response.total;// 更新总记录数
		});
	}

	// 查询实体
	$scope.findOne = function(id) {
		goodsService.findOne(id).success(function(response) {
			$scope.entity = response;
		});
	}

	// 保存
	$scope.save = function() {
		var serviceObject;// 服务层对象
		if ($scope.entity.id != null) {// 如果有ID
			serviceObject = goodsService.update($scope.entity); // 修改
		} else {
			serviceObject = goodsService.add($scope.entity);// 增加
		}
		serviceObject.success(function(response) {
			if (response.success) {
				// 重新查询
				$scope.reloadList();// 重新加载
			} else {
				alert(response.message);
			}
		});
	}

	// 增加
	$scope.add = function() {
		$scope.entity.goodsDesc.introduction = editor.html();
		goodsService.add($scope.entity).success(function(response) {
			if (response.success) {
				alert(response.message);
				$scope.entity = {};// 清空列表
				$scope.entity.goodsDesc.introduction = editor.html("");// 清空富文本编辑器
			} else {
				alert(response.message);
			}
		});
	}

	// 批量删除
	$scope.dele = function() {
		// 获取选中的复选框
		goodsService.dele($scope.selectIds).success(function(response) {
			if (response.success) {
				$scope.reloadList();// 刷新列表
			}
		});
	}

	$scope.searchEntity = {};// 定义搜索对象

	// 搜索
	$scope.search = function(page, rows) {
		goodsService.search(page, rows, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;
					$scope.paginationConf.totalItems = response.total;// 更新总记录数
				});
	}

	// 文件上传
	$scope.uploadFile = function() {
		uploadService.uploadFile().success(function(response) {
			if (response.success) {
				alert(response.message);
				$scope.image_entity.url = response.message;
			} else {
				alert(response.message);
			}
		}).error(function() {
			alert("上传错误!");
		});
	}

	// 定义页面实体结构
	$scope.entity ={goodsDesc:{itemImages:[],specificationItems:[]}};

	// 添加图片实体到列表
	$scope.add_image_entity = function() {
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
	}

	// 移除图片
	$scope.remove_image_entity = function(index) {
		$scope.entity.goodsDesc.itemImages.splice(index, 1);
	}

	// 查询一级分类列表
	$scope.selectItemCat1List = function() {
		$scope.itemCat2List = [];
		itemCatService.findByParentId(0).success(function(response) {
			$scope.itemCat1List = response;
		});
	}

	// 加载二级分类列表
	$scope.$watch('entity.goods.category1Id', function(newValue, oldValue) {
		$scope.itemCat3List = [];
		$scope.entity.goods.typeTemplateId = '';
		itemCatService.findByParentId(newValue).success(function(response) {
			$scope.itemCat2List = response;
		});
	});

	// 加载三级分类列表
	$scope.$watch('entity.goods.category2Id', function(newValue, oldValue) {
		$scope.entity.goods.typeTemplateId = '';
		itemCatService.findByParentId(newValue).success(function(response) {
			$scope.itemCat3List = response;
		});
	});

	// 读取到category3Id改变后,读取ID模板
	$scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {
		itemCatService.findOne(newValue).success(function(response) {
			$scope.entity.goods.typeTemplateId = response.typeId;
		});
	});

	// 当模板id更新后,更新模板对象
	$scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {
			//查询模板对象
			typeTemplateService.findOne($scope.entity.goods.typeTemplateId)
					.success(function(response) {
						$scope.typeTemplate = response;
						//将品牌列表json字符串转为json格式数组
						$scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
						//如果没有 ID，则加载模板中的扩展数据
						if($location.search()['id']==null){
							//将扩展属性类表json字符串转为json格式数组
							$scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
						}
						});
			
			//查询规格列表
			typeTemplateService.findSpecList(newValue).success(function(response){
				$scope.specList = response;
			});
	});
	
	//更新规格选项值specificationItems
	$scope.updateSpecAttribute = function($event,name,value){
		//alert("aaaa");
		var object = $scope.searchObjectByKey(
				$scope.entity.goodsDesc.specificationItems,'attributeName',name);
		if(object!=null){	
			if($event.target.checked ){
				object.attributeValue.push(value);		
			}else{//取消勾选				
				object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
				//如果选项都取消了，将此条记录移除
				if(object.attributeValue.length==0){
					$scope.entity.goodsDesc.specificationItems.splice(
							$scope.entity.goodsDesc.specificationItems.indexOf(object),1);
				}				
			}
		}
		else{
			$scope.entity.goodsDesc.specificationItems.push(
					{'attributeName':name,'attributeValue':[value]});
		}
	}
	
	//生成sku列表
	$scope.createItemList = function(){
		//初始化列表
		$scope.entity.itemList = [{spec:{},price:0,num:99999,status:'0',isDefault:'0'}];
		//将规格选项所选择的规格列表赋值给变量items.
		var items = $scope.entity.goodsDesc.specificationItems;
		//循环遍历items中的规格选项,加入到itemList的spec中
		for(var i=0;i<items.length;i++){
			$scope.entity.itemList = addColumn(
					$scope.entity.itemList,items[i].attributeName,items[i].attributeValue);
		}
	}
	
	//添加列值的方法(list:要显示的sku列表集合,columnName:规格的name,columnValues:规格的值[].
	addColumn = function(list,columnName,columnValues){
		var newList = [];
		for(var i=0;i<list.length;i++){
			var oldRow = list[i];
			for(var j=0;j<columnValues.length;j++){
				var newRow = JSON.parse(JSON.stringify(oldRow));//深克隆
				newRow.spec[columnName] = columnValues[j];
				newList.push(newRow);
			}
		}
		return newList;
	}
	
	//为了显示商品的状态,定义一个集合,集合的下标和状态名称相对应
	$scope.status=['未审核','已审核','审核未通过','关闭'];//相对饮的商品的状态0,1,2,3
	
	/*
	 * 显示一级~三级分类的名称 :
	 * 		首先定义一个用于封装返回数据的数组
	 * 		将分类列表从数据库中查询,然后根据返回数据的id和名称封装到一个数组.
	 */
	$scope.itemCatList = [];
	$scope.findItemCatList = function(){
		itemCatService.findAll().success(
			function(response){
				for(var i=0;i<response.length;i++){
					$scope.itemCatList[response[i].id] = response[i].name;	
				}
			}
		);
	}
	
	$scope.findOne = function(){
		var id = $location.search()['id'];
		if(id==null){
			return;
		}
		goodsService.findOne(id).success(function(response) {
			$scope.entity = response;
			//向富文本编辑器添加商品介绍
			editor.html($scope.entity.goodsDesc.introduction);
			//显示图片列表
			$scope.entity.goodsDesc.itemImages=
			JSON.parse($scope.entity.goodsDesc.itemImages);
			// 显示扩展属性
			$scope.entity.goodsDesc.customAttributeItems=
			JSON.parse($scope.entity.goodsDesc.customAttributeItems);
			// 规格
			$scope.entity.goodsDesc.specificationItems=JSON.parse(
					$scope.entity.goodsDesc.specificationItems);
			//SKU 列表规格列转换
			for( var i=0;i<$scope.entity.itemList.length;i++ ){
				$scope.entity.itemList[i].spec =
					JSON.parse( $scope.entity.itemList[i].spec);
			}
		});
	}
	
	
	//根据规格名称和选项名称返回是否被勾选
	$scope.checkAttributeValue=function(specName,optionName){
		var items= $scope.entity.goodsDesc.specificationItems;
		var object= $scope.searchObjectByKey(items,'attributeName',specName);
		if(object==null){
			return false;
		}else{
			if(object.attributeValue.indexOf(optionName)>=0){
				return true;
			}else{
				return false;
			
			}
		}
	}
});
