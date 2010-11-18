package tekdays

class TekEvent {
	// grails implicitly adds the following properties:
	// Integer	id			// <-- unique id in the db
	// Integer	version		// <-- version# used by grails for optimistic concurrency
	
	String	city
	String	name
	TekUser	organizer
	String 	venue
	Date	startDate
	Date	endDate
	String	description

	static hasMany = [	volunteers:TekUser,
						respondents:String,	// respondents are anonymous parties who want to receive email about an event
						sponsorships:Sponsorship,
						tasks:Task,
						messages:Message]
	
	// NOTE: the order the fields appear in the constraints
	// closure also determines the order they appear
	// in the dynamically scaffolded views
    static constraints = {
		name()
		city()
		description maxSize:5000	// <-- this will yield TEXT db datatype rather than default of VARCHAR(255)
		organizer()
		venue()
		startDate()
		endDate()
		volunteers nullable:true
		sponsorships nullable:true
		tasks nullable:true
		messages nullable:true
    }
	
	String toString() { "$name, $city" }
}
