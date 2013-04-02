
function SubscribedController($scope, $http, $window) {

	$scope.userName = window.top.document.URL.split("/")[5];
	
	

		// fill post container with data when window is loaded
	$(window).load(function() {
		pV = $("#postViewer");
		pVWindow = pV[0].contentWindow;
		
		pVWindow.angular
		.element($("#postContainer", pV.contents()))
		.scope().noDeleteButton(true);
				
		pVWindow.angular
		.element($("#postContainer", pV.contents()))
		.scope().getPosts();


   });	

};