package tekdays

class DashboardController {
		
    def index = {
		redirect action:"dashboard", params:params
	}
	
	def dashboard = {
		def event = TekEvent.get(params.id)
		if (event) {
			if (event.organizer.userName == session.user.userName || event.volunteers.collect{it.userName}.contains(session.user.userName)) {
				def tasks = Task.findAllByEventAndCompleted(event, false, [max:5, sort:'dueDate'])
				def volunteers = event.volunteers
				def messages = Message.findAllByEventAndParentIsNull(event, [sort:'id', order:'desc'])
				def sponsorships = event.sponsorships
				return [event:event, tasks:tasks, volunteers:volunteers, messages:messages, sponsorships:sponsorships]
			}
			else {
				flash.message = "Access to dashboard for ${event.name} denied."
			}
		}
		else {
			flash.message = "NO event was found with an id of ${params.id}"
			redirect controller:'tekEvent', action:'list'
		}
	}
}
