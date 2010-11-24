package tekdays

class Task {

	String		title
	String 		description
	TekUser		assignedTo
	Date		dueDate
	TekEvent	event
	Boolean 	completed

	static belongsTo = TekEvent
	
    static constraints = {
		title blank:false
		description blank:true, nullable:true, maxSize:5000
		assignedTo nullable:true
		dueDate nullable:true
		completed nullable:true
    }
	
	String toString() { title }
}
