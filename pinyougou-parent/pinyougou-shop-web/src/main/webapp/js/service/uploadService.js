app.service('uploadService',function($http){
	this.uploadFile=function(){
		var formData=new FormData();  
        //<form  enctype=” multipart/form-data”>
        //<input name=”file” type=”file” />
	    formData.append("file",file.files[0]);   
		return $http({
            method:'POST',
            url:"../upload.do",
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });		
	}	

});