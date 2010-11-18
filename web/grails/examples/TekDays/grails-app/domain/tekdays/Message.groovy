package tekdays

class Message {

	String		subject
	String		content
	Message 	parent
	TekEvent	event
	TekUser		author
	
	// means we'll get cascading deletes - delete and event and its msgs also deleted
	static belongsTo = TekEvent
	
    static constraints = {
		subject blank:false
		content blank:false, maxSize:2000
		parent nullable:true
		author nullable:false
    }
	
	String toString() { subject }
}
