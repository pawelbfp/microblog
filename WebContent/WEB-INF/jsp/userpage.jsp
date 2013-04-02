<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="lib/microblog/css/styles.css" rel="stylesheet" type="text/css" />
<script src="lib/jquery/jquery.js"></script>
<script src="lib/angularjs/js/angular.js"></script>

<script src="lib/microblog/js/UserpageController.js"></script>
<title>User page</title>
</head>
<body>
	<tag:menu username="${userName}"></tag:menu>
	<div class="leftTopMargin" ng-controller="UserpageController">
		<div class="container">
			<div class="title center defRowHeight">
				<h2>User page</h2>
			</div>
			<div class="displaytable">
				<div id="leftContainer" class="displaycell">
					<div class="label listItem">My posts</div>
					<iframe id="postViewer" class="iframeStyle" src="postViewer"></iframe>
				</div>


				<div id="rightContainer" class="displaycell">
					<div class="label listItem ">Post :</div>
					<textarea id="postBody" rows="4" cols="40" maxlength="140" onKeyDown="limitText(1);" onKeyUp="limitText(2);"
						placeholder="Write your post body here"></textarea>
					<div>
						You have <span id="countdown">140</span> characters left.
					</div>
					<div>
						<span id="postError" class="label label-important span4 notvisible">* post cannot be empty</span>
					</div>
					<button id="postBtn" class="btn left" ng-click="addPost()">Add post</button>

				</div>

			</div>
		</div>
	</div>

</body>
</html>