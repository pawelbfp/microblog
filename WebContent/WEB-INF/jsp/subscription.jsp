<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="lib/microblog/css/styles.css" rel="stylesheet"
	type="text/css" />
<script src="lib/jquery/jquery.js"></script>
<script	src="lib/angularjs/js/angular.js"></script>
<script src="lib/microblog/js/SubscriptionController.js"></script>
<title>Subscription page</title>
</head>
<body ng-controller="SubscriptionController">
	<tag:menu username="${userName}"></tag:menu>
	<div class="leftTopMargin" >
		<div class="container">
			<div class="title center defRowHeight">
				<h2>Subscription</h2>
			</div>
			<div class="displaytable">
				<div id="leftContainer" class="displaycell">
					<div class="label listItem">Aviable users</div>
					<select id="availableUsers" multiple="multiple" class="span4 pre-scrollable defSelecteight">
						<option ng-repeat="user in availableUsers" id="{{user.idUser}}">{{user.userName}}</option>
					</select>
				</div>
				<div id="centerContainer" class="displaycell center span2">
					<div class="row messgeRow" >
						<button id="add" class="btn">Add</button>
					</div>
					<div class="row messgeRow"  >
						<button id="addAll" class="btn">Add all</button>
					</div>
					<div class="row messgeRow" >
						<button id="remove" class="btn">Remove</button>
					</div>
					<div class="row messgeRow" >
						<button id="removeAll" class="btn">Remove All</button>
					</div>
				</div>
				<div id="rightContainer" class="displaycell">
					<div class="label listItem ">Selected users</div>
					<select id="selectedUsers" multiple="multiple" class="span4 pre-scrollable defSelecteight">
						<option ng-repeat="user in subscribedUsers" id="{{user.idUser}}">{{user.userName}}</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</body>
</html>