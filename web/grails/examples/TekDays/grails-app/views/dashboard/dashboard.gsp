<html>
	<head>
		<title>TekDays</title>
		<meta name="layout" conent="main"/>	<%-- means we'll merge with the main.gsp template --%>
	</head>
	<body>
		<%-- This page is pretty complicated, so we'll break it up into several templates... --%>
		<div id="event" style='margin:10px 10px 10px 10px'>
			<g:render template="event" model="${['event':event]}"/>
		</div>
		<div id="tasks" style='margin:10px 10px 10px 10px'>
			<g:render template="tasks" model="${['tasks':tasks]}"/>
		</div>
		<div id="volunteers" style='margin:10px 10px 10px 10px'>
			<g:render template="volunteers" model="${['volunteers':volunteers]}"/>
		</div>
		<div id="messages" style='margin:10px 10px 10px 10px'>
			<g:render template="messages" model="${['messages':messages]}"/>
		</div>
		<div id="sponsors" style='margin:10px 10px 10px 10px'>
			<g:render template="sponsors" model="${['sponsorships':sponsorships]}"/>
		</div>
	</body>
</html>