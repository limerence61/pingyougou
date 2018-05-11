//brandService
app.service('brandService',
		function($http) {

			this.findAll = function() {
				return $http.get('../brand/findAll.do');
			}

			this.findPage = function(page, size) {
				return $http.get('../brand/findPage.do?page=' + page + '&size='
						+ size);
			}

			this.findOne = function(id) {
				return $http.get('../brand/findOne.do?id=' + id);
			}

			this.add = function(entity) {
				return $http.post('../brand/add.do', entity);
			}

			this.update = function(entity) {
				return $http.post('../brand/update.do', entity);
			}

			this.del = function(selectedIds) {
				return $http.get('../brand/delete.do?ids=' + selectedIds);
			}

			this.search = function(page, size, entity) {
				return $http.post('../brand/search.do?page=' + page + '&size='
						+ size, entity);
			}
			
			this.selectBrandList = function(){
				return $http.get('../brand/selectBrandList.do');
			}

		});