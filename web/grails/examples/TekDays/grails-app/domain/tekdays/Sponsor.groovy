package tekdays

/*
 * Issues with this class:
 * 
 * There may be multiple sponsors for a singe event, and a sponsor
 * might sponsor multiple different events, so there is a many-to-many
 * relationship between Sponsor and Event.  This is supported implicitly
 * in grails by just adding each class to the other's hasMany property,
 * but things can get complicated in the database...
 * 
 * One class in the many-many relationship should be declared to be the
 * "owning" side.  For this, you have to use the belongsTo property.
 * 
 * So a Sponsor may have may TekEvents, but it also belongs to a particular
 * TekEvent.
 * 
 */
class Sponsor {

	String	name
	String	website
	String	description
	byte[]	logo		// an image file of the sponsor's logo
	
	static hasMany = [sponsorships:Sponsorship]
	
//	static belongsTo = TekEvent	// <-- don't need this anymore, right?
	
    static constraints = {
    	name blank:false
		website blank:false
		description nullable:false, maxSize:5000
		logo nullable:true, maxSize:1000000
		sponsorships nullable:true
	}
	
	String toString() { name }
}
