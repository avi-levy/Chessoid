package tekdays

import org.codehaus.groovy.grails.test.support.ControllerNameExtractor;

class SecurityFilters {

    def filters = {
    	doLogin(controller:'*', action:'*') {
			before = {
				if (!controllerName) {
					return true
				}
				def allowedActions = ['show', 'index', 'list', 'login', 'validate']
				if (!session.user && !allowedActions.contains(actionName)) {
					println "======================================================================================================================="
					println "  SECURITY: Controller: '${controllerName}', Action: '${actionName}' requires authentication, redirecting to login page." 
					println "=======================================================================================================================\n"
					redirect controller:'tekUser', action:'login', params:['cName':controllerName, 'aName':actionName]
					return false
				}
			}
		}
    }
}
