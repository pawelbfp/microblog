function PostViewerController($scope, $http) {

	$scope.userName = window.top.document.URL.split("/")[5];
	$scope.posts;
	$scope.users;
	$scope.deleteButton = true;
	$scope.delay = 2000;

	// hide delete button
	$scope.noDeleteButton = function() {
		$scope.deleteButton = false;
	};

	// transform date to string , if not correct return empty string
	$scope.dateToString = function(date) {

		var result = "";

		if (date != undefined) {
			try {
				var day = date.split(".")[0];
				var dayI;
				if (day[0] == '0')
					dayI = parseInt(day[1]);
				else
					dayI = parseInt(day);
				var month = date.split(".")[1];
				var monthI;
				if (month[0] == '0')
					monthI = parseInt(month[1]);
				else
					monthI = parseInt(month);

				var year = date.split(".")[2].split(" ")[0];
				var yearI = parseInt(year);
				var hour = date.split(" ")[1].split(":")[0];
				var hourI = parseInt(hour);
				var min = date.split(" ")[1].split(":")[1];
				var minI = parseInt(min);
				var sec = date.split(" ")[1].split(":")[2];
				var secI = parseInt(sec);
				if (dayI < 1 || dayI > 31 || monthI < 1 || monthI > 12
						|| yearI < 1900 || yearI > 2100 || hourI > 24
						|| minI > 59 || secI > 59 || isNaN(yearI))
					result = "";
				else
					result = year + month + day + hour + min + sec;
			} catch (e) {
				result = "";
			}
		}
		return result;
	};

	// date order function - change date to string in order to enable comparison
	$scope.dateOrder = function(post) {
		var date = "";
		if (post != undefined)
			date = $scope.dateToString(post.date);

		return date;
	};

	// date filter function - filter by dates taken from : from and to inputs
	$scope.dateFilter = function(post) {

		var zero = " 00:00:00";
		var min = $("#from").val();
		var max = $("#to").val();
		if (min != "") {
			min += zero;
			min = $scope.dateToString(min);
		}
		if (max != "") {
			max += zero;
			max = $scope.dateToString(max);
		}

		return (min === "" || $scope.dateToString(post.date) >= min)
				&& (max === "" || $scope.dateToString(post.date) <= max);
	};

	// get posts function
	// get all posts from user given in path
	$scope.getPosts = function() {

		var subscribedUser = $('.active').attr("data-value");
		var urlToExecute = "/MicroblogPL/posts/" + $scope.userName;
		var selector = $("#selector");
		if (selector.css("display") != "none")
			urlToExecute += "/" + subscribedUser;

		var config = {
			method : "GET",
			url : urlToExecute,
		};
		$http(config).success(function(data, status, headers, config) {
			for ( var i = 0; i < data.length; i++)
				data[i].noDelete = $scope.deleteButton;

			if ($scope.newPostData(data, $scope.posts))
				$scope.posts = data;
		});
	};

	// check if received post data are different that this currently on page
	$scope.newPostData = function(latest, client) {
		if (client == undefined && latest != undefined)
			return true;
		if (client != undefined && latest != undefined) {
			if (client.length != latest.length)
				return true;
			for ( var i = 0; i < latest.length; i++)
				if (client[i].date != latest[i].date
						|| client[i].body != latest[i].body
						|| client[i].userName != latest[i].userName)
					return true;
		}

		return false;

	};

	// get subscribed posts function
	// get all subscribed posts for user given in path
	$scope.getAllSubscribed = function(userName) {
		var config = {
			method : "GET",
			url : "/MicroblogPL/subscribedUsernames/" + userName,
		};
		$http(config).success(function(data, status, headers, config) {
			$scope.users = data;
			$("#aviableUsers").typeahead({
				source : data,
				updater : function(item) {
					$scope.getPosts();
					return item;
				}
			});
		});

	};

	// post delete by idPost
	$scope.deletePost = function(idPost) {
		var conf = confirm("Are you sure you want to delete this post?");

		if (conf == true) {

			var config = {
				url : "/MicroblogPL/posts/" + idPost,
				method : "DELETE",
			};
			$http(config).success(function(data, status, headers, config) {
				$scope.getPosts();
			});
		}
		;
	};

	$scope.refresh = function() {
		$scope.$apply();
	};

	// call get posts when there is no delete button
	$scope.checkNewPosts = function() {
		if (!$scope.deleteButton)
			$scope.getPosts();
	};

	$(window).load(function() {

		$("#from").bind("change", $scope.refresh);
		$("#to").bind("change", $scope.refresh);

		$scope.getAllSubscribed($scope.userName);
		setInterval($scope.checkNewPosts, $scope.delay);
	});

	// date picker functionality
	$(function() {

		$("#from").datepicker({
			defaultDate : "+1w",
			dateFormat : 'dd.mm.yy',
			changeMonth : true,
			numberOfMonths : 3,
			onClose : function(selectedDate) {
				$("#to").datepicker("option", "minDate", selectedDate);
				$scope.filterItems();
			}
		});

		$("#to").datepicker({
			defaultDate : "+1w",
			dateFormat : 'dd.mm.yy',
			changeMonth : true,
			numberOfMonths : 3,
			onClose : function(selectedDate) {
				$("#from").datepicker("option", "maxDate", selectedDate);
			}
		});
	});

};

