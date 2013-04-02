<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta charset="utf-8" />
<title>jQuery UI Datepicker - Select a Date Range</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<script src="lib/bootstrap/js/bootstrap-typeahead.js"></script>
<link href="lib/microblog/css/styles.css" rel="stylesheet" type="text/css" />
<script src="lib/angularjs/js/angular.js"></script>

<script src="lib/microblog/js/PostViewerController.js"></script>
</head>
<body>
	<div ng-controller="PostViewerController">
		<div class="displaytable">
			<label for="from" class="displaycell">From</label> <input type="text" id="from" name="from" class="displaycell" ng-model="filterFrom" />
			<label for="to" class="displaycell">to</label> <input type="text" id="to" name="to" class="displaycell" ng-model="filterTo" />
		</div>
		<div id="selector">
			<input type="text" class="span3 nomargin" id="aviableUsers" data-provide="typeahead" data-items="4" />


		</div>
		<div id="postContainer" class="postContainer">
			<div class="post" ng-repeat="post in posts | filter:dateFilter | orderBy:dateOrder:true">
				<div class="postBody">{{post.body}}</div>
				<div class=" postFooter">
					<span class="postOwner">{{post.userName}}</span> <span class="postDate">{{post.date}}</span> <span class="postDelete"
						id="{{post.idPost}}" ng-show="post.noDelete" onclick="angular.element(this).scope().deletePost(this.id)">x</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>