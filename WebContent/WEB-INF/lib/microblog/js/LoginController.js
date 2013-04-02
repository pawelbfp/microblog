
 
 function LoginController($scope, $http, $window) {
		$scope.login = "";
		$scope.password = "";
		$scope.errorMessage = "";

		$scope.signIn = function() {
			var config = {
				method: 'POST',
				params: {
					username: $scope.username,
					password: $scope.password
				},
				url: 'MicroblogPL/login_processing_url',
				headers: {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}			
			};		
			$http(config).success(successResponseHandler).error(errorResponseHandler);
		};
		
		function successResponseHandler(data, status, headers, config) {
			if (headers().error == 0) {
				$window.location.href = data; 
			} else {
				$scope.errorMessage = "Username or password not correct";
			}
		};

		function errorResponseHandler(data, status, headers, config) {					
			$scope.errorMessage = createErrorLabel(status);
		};
	}

	function createErrorLabel(status) {
		switch (status)
		{
			case 500:
			  return "Error " + status + ": Internal Server Error";
			case 501:
			  return "Error " + status + ": Not Implemented";
			case 502:
			  return "Error " + status + ": Bad Gateway";
			case 503:
			  return "Error " + status + ": Service Unavailable";
			case 504:
			  return "Error " + status + ": Gateway Timeout";
			case 505:
			  return "Error " + status + ": HTTP Version Not Supported";
			default:
			  return "Error occured";						  
		}
		
		
	};
	
	 $(document).ready(function() {
		   
		 
		 //hide/show validation messages 
		   $("#login").on("click",function(event){
			   
			  var notSubmit=true; 
			  if  ($("#username").val()===""){
				  $("#usrError").removeClass("notvisible");
				  notSubmit = false;
			  }
			  else
				  $("#usrError").addClass("notvisible");
			  if  ($("#password").val()===""){
				  $("#pwdError").removeClass("notvisible");
				  notSubmit = false;
			  }
			  else
				  $("#pwdError").addClass("notvisible");	  
				  
			  if (notSubmit==false)
			 {
				  event.stopPropagation();
				  event.preventDefault();
			 }
				  
		   });
		 });