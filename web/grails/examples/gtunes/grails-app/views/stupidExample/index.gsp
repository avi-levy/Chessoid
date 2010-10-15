<!--  a page directive: -->

<!--  contentType page directive informs an interpreter of the kind of
		data to expect, HTML in this case -->
<%@ page contentType="text/html; charset=ISO-8859-1" %>

<!--  import page directive works just like import statements
		in java/groovy sources.  note that groovy imports the
		java.lang, java.lang.util, java.io, java.net, groovy.lang,
		and groovy.util packages by default-->
<%@ page import="java.sql.Time" %>

<html>
	<body>
		<!--  this is a "scriptlet", a concept from JSP, where we have
				a bunch of code that gets executed on the server-side -->
		<% 3.times { %>
			<p>I am printed three times!</p>
		<% } %>
		
		<%
			out << "print me (using the 'out' object)!"
		 %>
	</body>
</html>