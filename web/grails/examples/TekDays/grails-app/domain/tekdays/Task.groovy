package tekdays

class Task {

	String		title
	String 		description
	String		assignedTo
	Date		dueDate

	static belongsTo = TekEvent
	
    static constraints = {
		title blank:false
		notes blank:true, nullable:true, maxSize:5000
		assignedTo nullable:true
		dueDate nullable:true
    }
	
	String toString() { title }
}
