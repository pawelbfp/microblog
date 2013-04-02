function SubscriptionController($scope, $http, $window) {

	$scope.userName = window.top.document.URL.split("/")[5];
	$scope.availableUsers;
	$scope.subscribedUsers;

	// get available users to be subscribed
	$scope.getAvailable = function() {
		var config = {
			method : "GET",
			url : "/MicroblogPL/availableUserNames/" + $scope.userName,
		};

		$http(config).success(function(data, status, headers, config) {
			$scope.availableUsers = data;
		});
	};

	// get subscribed users names
	$scope.getSubscribed = function() {
		var config = {
			method : "GET",
			url : "/MicroblogPL/subscrubedUserNames/" + $scope.userName,
		};

		$http(config).success(function(data, status, headers, config) {
			$scope.subscribedUsers = data;
		});
	};

	// add subscription - add selected usernames from available to selected and
	// commit changes to database
	$scope.add = function(param) {
		var sel = $('#availableUsers').find(param);
		var addList = new Array();
		for ( var i = 0; i < sel.length; i++) {
			var subscr = {
				idUser : sel[i].id,
				userName : sel[i].value
			};
			addList[i] = subscr;
		}
		;

		var config = {
			method : "POST",
			url : "/MicroblogPL/addsubscription/",
			data : JSON.stringify(addList),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
		};

		$http(config).success(function(data, status, headers, config) {
			console.log("add ok");
		}).error(function(data, status, headers, config) {
			console.log("add fail");
		});

		$('#selectedUsers').append($('#availableUsers').find(param));
		sortLists();
	};

	$("#add").on("click", function(event) {
		$scope.add(":selected");
	});

	$("#addAll").on("click", function(event) {
		$scope.add("option");
	});

	// add subscription - add selected userNames from selectedUsers to
	// availableUsers and commit changes to database
	$scope.remove = function(param) {
		var sel = $('#selectedUsers').find(param);
		var removeList = new Array();
		for ( var i = 0; i < sel.length; i++) {
			var subscr = {
				idUser : sel[i].id,
				userName : sel[i].value
			};
			removeList[i] = subscr;
		}
		;

		var request = $.ajax({
			url : "/MicroblogPL/unsubscribe/",
			type : "delete",
			data : JSON.stringify(removeList),
			dataType : "json",
			contentType : "application/json; charset=utf-8",

		});

		// callback handler that will be called on success
		request.done(function(response, textStatus, jqXHR) {

			$('#availableUsers').append($('#selectedUsers').find(param));
			sortLists();

		});

		// callback handler that will be called on failure
		request.fail(function(jqXHR, textStatus, errorThrown) {
			// log the error to the console
			console.error("The following error occured: " + textStatus,
					errorThrown);
		});

		// callback handler that will be called regardless
		// if the request failed or succeeded
		request.always(function() {

		});
	};

	$("#remove").on("click", function(event) {

		$scope.remove(":selected");

	});

	$("#removeAll").on("click", function(event) {
		$scope.remove("option");
	});

	// sort userNames on the list
	function sortList(select) {
		arrTexts = new Array();

		var i;
		for (i = 0; i < select.length; i++) {
			arrTexts[i] = select.options[i].text;
		}

		arrTexts.sort();

		for (i = 0; i < select.length; i++) {
			select.options[i].text = arrTexts[i];
			select.options[i].value = arrTexts[i];
		}
	}

	function sortLists() {
		sortList($('#selectedUsers')[0]);
		sortList($('#availableUsers')[0]);
	}

	// fill lists with data when document i ready
	$(document).ready(function() {
		$scope.getSubscribed();
		$scope.getAvailable();
	});
}
