import tekdays.Sponsor 
import tekdays.Sponsorship 
import tekdays.TekEvent 
import tekdays.TekUser 


class BootStrap {

    def init = { servletContext ->
		if (!TekEvent.get(1)) {
			/*
			 * USERS
			 */
			def user1 = new TekUser(
				fullName:'John Doe',
				userName:'jdoe',
				password:'t0ps3cr3t',
				email:'jdoe@johnsgroovyshop.com',
				website:'blog.johnsgroovyshop.com',
				bio:'''John has been programming for 100,000 years....''')
			if (!user1.save()) {
				user1.errors.allErrors.each { error ->
					println "An error occured with user1: ${error}"
				}
			}
			def user2 = new TekUser(
				fullName:'John Deere',
				userName:'tractorman',
				password:'t0ps3cr3t',
				email:'john.deere@porkproducers.org',
				website:'www.perl.porkproducers.org',
				bio:'John is a top notch perl developer and...')
			if (!user2.save()) {
				user2.errors.allErrors.each { error ->
					println "An error occured with user2: ${error}"
				}
			}
			def smartin = new TekUser(
				fullName:'Sarah Martin',
				userName:'sarah',
				password:'54321`',
				email:'sarah@martinworld.com',
				website:'www.martinworld.com',
				bio:'''Web designer and Grails aficionado''')
			if (!smartin.save(flush:true)) {
				smartin.errors.allErrors.each { error ->
					println "An error occured with smartin: ${error}"
				}
			}
			def mrbill = new TekUser(
				fullName:'Bill Smith',
				userName:'Mr_Bill',
				password:'12345`',
				email:'mrbill@email.com',
				website:'www.mrbillswebsite.com',
				bio:'''Software developer, claymation artist''')
			if (!mrbill.save(flush:true)) {
				mrbill.errors.allErrors.each { error ->
					println "An error occured with mrbill: ${error}"
				}
			}
			/*
			 * EVENTS
			 */
			def event1 = new TekEvent(
								name:'Gateway Code Camp',
								city:'Saint Louis, MO',
								organizer:user1,
								venue:'TDB',
								startDate:new Date('9/19/2012'),
								endDate:new Date('9/25/2012'),
								description:'''This conferenence will bring coders from
											far and wide to...''')
			event1.addToVolunteers(smartin)
			event1.addToVolunteers(mrbill)
			event1.addToRespondents('ben@grailsmail.com')
			event1.addToRespondents('zachary@linuxgurus.com')
			event1.addToRespondents('solomon@bootsrapwelding.com')
			if (!event1.save(flush:true)) {
				event1.errors.allErrors.each { error ->
					println "An error occured with event1: ${error}"			
				}
			}
			def event2 = new TekEvent(
				name:'Perl Before Swine',
				city:'Austin, MN',
				organizer:user2,
				venue:'SPAM Museum',
				startDate:new Date('9/14/2013'),
				endDate:new Date('9/18/2013'),
				description:'''Funny, jokey perl programming
							conference.  Probably not for you, 
							or anybody else...''')
			if (!event2.save(flush:true)) {
				event2.errors.allErrors.each { error ->
					println "An error occured with event2: ${error}"
				}
			}
			/*
			 * SPONSORS	
			 */
			def s1 = new Sponsor(
							name:'Contegix',
							website:'contegix.com',
							description:'Beyond Managed Hosting for your Enterprise!!!')
			if (!s1.save(flush:true)) {
				s1.errors.allErrors.each { error ->
					println "An error occured with s1: ${error}"
				}
			}
			def s2 = new Sponsor(
				name:'Object Computing Incorporated',
				website:'ociweb.com',
				description:'Beyond Object Oriented Technologies for your Enterprise!!!')
			if (!s2.save(flush:true)) {
				s2.errors.allErrors.each { error ->
					println "An error occured with s2: ${error}"
				}
			}
			/*
			 * TODO: SPONSORSHIPS
			 */
			def sp1 = new Sponsorship(
				event:event1,
				sponsor:s1,
				contributionType:'Other',
				description:'T-shirts')
			def sp2 = new Sponsorship(
				event:event1,
				sponsor:s2,
				contributionType:'Venue',
				description:'Will be paying for the Moscone')
			s1.addToSponsorships(sp1)
			s1.save()
			s2.addToSponsorships(sp2)
			s2.save()
			event1.addToSponsorships(sp1)
			event1.addToSponsorships(sp2)
			event1.save()
    	}
    }
	
    def destroy = {
    }
}
