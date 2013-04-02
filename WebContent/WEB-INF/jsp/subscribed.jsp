<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="lib/microblog/css/styles.css" rel="stylesheet" type="text/css" />
<script src="lib/jquery/jquery.js"></script>
<script	src="lib/angularjs/js/angular.js"></script>

<script src="lib/microblog/js/SubscribedController.js"></script>

<title>Subscribed users posts</title>
</head>
<body>
	<tag:menu username="${userName}"></tag:menu>
	<div class="leftTopMargin">
		<div class="container" ng-controller="SubscribedController">
			<div class="title center defRowHeight">
				<h2>Subscribed users posts</h2>
			</div>
			<iframe id="postViewer" class="iframeStyle span4" src="postViewer"></iframe>
		</div>
	</div>
</body>
</html>