package tekdays

class TaskService {

    static transactional = true

    def serviceMethod() {
    }
	
	def addDefaultTasks(tekEvent) {
		if (tekEvent.tasks?.size() > 0) {	// we only want to add tasks to new events
			return
		}
		tekEvent.addToTasks new Task(title:'Identify potential venues')			//TODO: externalize strings
		tekEvent.addToTasks new Task(title:'Get price / availability of venues')
		tekEvent.addToTasks new Task(title:'Compile potential sponsor list')
		tekEvent.addToTasks new Task(title:'Design promotional materials')
		tekEvent.addToTasks new Task(title:'Compile potential advertising avenues')
		tekEvent.addToTasks new Task(title:'Locate swag provider (perferably local)')
		tekEvent.save()
	}
}
