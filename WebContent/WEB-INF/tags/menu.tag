<%@ tag body-content="empty" trimDirectiveWhitespaces="true" language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="username"%>

<div class="navbar navbar-fixed-top ">
	<div class="navbar-inner pull-right">
		<ul class="nav">
			<li class="active "><a href="/MicroblogPL/userpage/${username}">My posts</a></li>
			<li class="divider-vertical"></li>
			<li><a href="/MicroblogPL/subscription/${username}">Subscription</a></li>
			<li class="divider-vertical"></li>
			<li><a href="/MicroblogPL/subscribed/${username}">Subscribed user posts</a></li>
			<li class="divider-vertical"></li>
			<li><a href="#">Hello ${username}</a></li>
			<li class="divider-vertical"></li>
			<li><a href="/MicroblogPL/logout_processing_url">Log out</a></li>
		</ul>
	</div>
</div>

