function UserpageController($scope, $http, $window) {

	$scope.userName = window.top.document.URL.split("/")[5];
	$scope.limitNum = 140;
	$scope.postError = $("#postError");
	
	//add post to database
	$scope.addPost = function() {
		
		var body = $("#postBody").val();
		if (body=="")
		{
			$scope.postError.removeClass("notvisible");
			return true;
		}
		
		var config = {
			url : "/MicroblogPL/posts/",
			method : "POST",
			data : body,
		};
		$http(config).success(
				function(data, status, headers, config) {
					pVWindow.angular
							.element($("#postContainer", pV.contents()))
							.scope().getPosts();
				});
	};

	//fill window with posts
	$(window).load(
			function() {
				pV = $("#postViewer");
				pVWindow = pV[0].contentWindow;
				selector = $("#selector", pV.contents());

				selector.css("display", "none");
				pVWindow.angular.element($("#postContainer", pV.contents()))
						.scope().getPosts();

			});
};


//limit textarea to 140 characters
function limitText() {
	limitField = $("#postBody");
	limitCount = $("#countdown");
	limitNum   = 140; 
	if (limitField.val().length > limitNum) {
		limitField.val(limitField.val().substring(0, limitNum));
	} else {
		limitCount.html(limitNum - limitField.val().length);
	}
	
	if (limitField.val().length > 0 )
		$("#postError").addClass("notvisible");
};
