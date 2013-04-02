<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="lib/microblog/css/styles.css" rel="stylesheet" type="text/css" />
<script src="lib/angularjs/js/angular.js"></script>
<script src="lib/jquery/jquery.js"></script>
<script src="lib/microblog/js/LoginController.js"></script>
<title>Microblog login page</title>
</head>
<body>
	<div class="leftTopMargin">
		<div class="container" ng-controller="LoginController">
			<div class="title center defRowHeight">
				<h2>Practice Microblog Login</h2>
			</div>
			<div class="row">
				<form class="center formStyle" ng-submit="signIn()">


					<div class="defRowHeight">
						<div class="form-inline">
							<label>User name</label><label class="span4 notvisible">xxx</label>
						</div>

						<div>
							<input id="username" type="text" ng-model="username" /><span id="usrError" class="label label-important span4 notvisible">*
								username cannot be empty</span>

						</div>
					</div>
					<div class="defRowHeight">
						<div class="form-inline">
							<label>Password</label><label class="span4 notvisible">xxx</label>
						</div>

						<div>
							<input id="password" type="password" ng-model="password" /> <span id="pwdError" class="label label-important span4 notvisible">*
								password cannot be empty</span>
						</div>
					</div>
					<div class="row messgeRow">
						<span class="label label-important span4">${message}</span> <span class="label label-important span4">{{errorMessage}}</span>

					</div>

					<div class="">

						<button id="login" class="btn">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


