package tekdays

class TekDaysTagLib {
	
	// Stupid example, just reverses the body of the tag.
	// This is all you have to do.  How cool. (no config files!)
	def backwards = { attrs, body ->
		// attrs is a map of any attributes the tag needs
		// body is a closure representing the body of the tag
		out << body().reverse()
	}
	
	def messageThread = { attrs ->
		def messages = attrs.messages.findAll{msg -> !msg.parent}	// all the messages that aren't replies
		processMessages(messages, 0)
	}
	
	def processMessages(messages, indent) {
		messages.each {msg ->
			def body = "${msg?.author} - ${msg?.subject}"
			out << "<div style='height:30; margin-left:${indent*20};'>"
			out << "${remoteLink(action:'showDetail', id:msg.id, update:'details', body)}"
			out << "</div>"
			def children = Message.findAllByParent(msg)
			if (children) {
				processMessages(children, indent+1)
			}
		}
	}
	
	def loginToggle = {
		out << "<div>"
		if (session.user) {
			out << "<span style='float:left'>"
			out << "Welcome ${session.user}."
			out << "</span><span style='float:right;margin-right:10px'>"
			out << "<a href='${createLink(controller:'tekUser', action:'logout')}'>"
			out << "Logout </a></span>"
		}
		else {
			out << "<span style='float:right;margin-right:10px'>"
			out << "<a href='${createLink(controller:'tekUser', action:'login')}'>"
			out << "Login </a></span>"
		}
		out << "</div><br/>"
	}
}
