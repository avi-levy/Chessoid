package com.gtunes

class UserController {
	
	def login = { LoginCommand cmd ->
		if (request.method == 'POST') {
			if (!cmd.hasErrors()) {
				session.user = cmd.getUser()
				redirect(controller:'store')
			}
			else {
				render(view:'/store/index', model:[loginCmd:cmd])
			}
		}
		else {
			render(view:'/store/index')
		}
	}

    def register = {
        if (request.method == 'POST') {
            
			/*
			 * Note that right here the relevant fields in the params object 
			 * will be automatically bound to the relevant attributes of User
			 * based on naming conventions (params.login will be bound to
			 * User.login, params.password to User.password, etc).
			 */
			def u = new User(params)
            
			/*
			 * Does the password confirmation match?  If so,
			 * go ahead and save the new user to the database and
			 * redirect back to the store.
			 */
			if (u.password != params.confirm) {
				/*
				 * rejectValue() comes from Spring (implements
				 * org.springframework.validation.Errors interface):
				 * rejectValue(String nameOfField, String errorCode) 
				 */
				u.errors.rejectValue("password", "user.password.dontmatch")
				/*
				 * return a model so the register view can display the errors
				 */
				return [user:u]
			}
			else if (u.save()) {
				session.user = u
				redirect(controller:"store")				
			}
			else {
				/*
				 * return a model so the register view can display the errors
				 */
				return [user:u]
			}
        }
    }
}

class LoginCommand {
	String login
	String password
	private u
	
	User getUser() {
		if (!u && login) {
			u = User.findByLogin(login, [fetch:[purchasedSongs:'join']])
		}
		return u
	}
	
	static constraints = {
		login blank:false, validator:{val, cmd ->
			if (!cmd.user) {
				return "user.not.found"
			}
		}
		password blank:false, validator:{val, cmd ->
			if (cmd.user && cmd.user.password != val) {
				return "user.password.invalid"
			}
		}
	}
}
