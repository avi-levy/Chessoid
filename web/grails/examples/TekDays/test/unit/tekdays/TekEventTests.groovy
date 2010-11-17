package tekdays

import grails.test.*

class TekEventTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {
		def tekEvent = new TekEvent(name:'Groovy One',
									city:'San Francisco, CA',
									organizer:[fullname:'John Doe'] as TekUser,		// <-- book suggests this kind of mocking
									venue:'Moscone Center',
									startDate:new Date('6/2/2011'),
									endDate:new Date('6/5/2011'),
									description:'This conference will cover all...')
		assertEquals 'Groovy One, San Francisco, CA', tekEvent.toString()
    }
}
