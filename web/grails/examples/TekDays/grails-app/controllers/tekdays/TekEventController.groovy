package tekdays

class TekEventController {
	
	def taskService		// <-- gets auto-wired to an instance of TaskService based on naming convention 

	// the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	// controller actions:
	
	/*
	 * There are 3 ways to properly exit a controller action:
	 * 1. render()
	 * 2. redirect()
	 * 3. return null or a map containing model data
	 */
   
	// default action, just redirects to list action
    def index = {
        redirect(action: "list", params: params)
    }

	// show all instances
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tekEventInstanceList: TekEvent.list(params), tekEventInstanceTotal: TekEvent.count()]
    }

	// create a new instance
    def create = {
        def tekEventInstance = new TekEvent()
        tekEventInstance.properties = params
        return [tekEventInstance: tekEventInstance]
    }

	// save a particular instance
	// flash is a map that exists in a special scope which is available only for the current request and the following request
	// similar to update action but without the concurrency check (save is called when creating new instances, update is like
	// 'save' for existing instances and so needs protection against concurrent modification)
    def save = {
        def tekEventInstance = new TekEvent(params)
        if (tekEventInstance.save()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])}"
            taskService.addDefaultTasks(tekEventInstance)
			tekEventInstance.save(flush:true)				// <-- really push it out to DB *now* 
			redirect(action: "show", id: tekEventInstance.id)
        }
        else {
            render(view: "create", model: [tekEventInstance: tekEventInstance])
        }
    }
	
	// show a particular instance
    def show = {
        def tekEventInstance = TekEvent.get(params.id)
        if (!tekEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tekEventInstance: tekEventInstance]
        }
    }

	// the edit action doesn't do any actual updating iteself, it just loads
	// up the appropriate instance and lets the update action do any editing.
	// except for the name (which determines which view gets rendered) the
	// edit action is the same as the show action
    def edit = {
        def tekEventInstance = TekEvent.get(params.id)
        if (!tekEventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tekEventInstance: tekEventInstance]
        }
    }

	// called when changes from the edit view are submitted
    def update = {
        def tekEventInstance = TekEvent.get(params.id)
        if (tekEventInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tekEventInstance.version > version) {
                    
                    tekEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tekEvent.label', default: 'TekEvent')] as Object[], "Another user has updated this TekEvent while you were editing")
                    render(view: "edit", model: [tekEventInstance: tekEventInstance])
                    return
                }
            }
            tekEventInstance.properties = params
            if (!tekEventInstance.hasErrors() && tekEventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), tekEventInstance.id])}"
                redirect(action: "show", id: tekEventInstance.id)
            }
            else {
                render(view: "edit", model: [tekEventInstance: tekEventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
            redirect(action: "list")
        }
    }

	// delete an instance
    def delete = {
        def tekEventInstance = TekEvent.get(params.id)
        if (tekEventInstance) {
            try {
                tekEventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])}"
            redirect(action: "list")
        }
    }
}
