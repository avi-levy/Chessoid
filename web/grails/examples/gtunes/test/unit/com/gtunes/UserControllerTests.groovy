package com.gtunes

import grails.test.*

class UserControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPasswordsDontMatch() {
		mockRequest.method = 'POST'
		mockDomain User 
		mockParams.login = "joelh"
		mockParams.password = 'mst3k'
		mockParams.confirm = 'mst3j'
		mockParams.firstName = 'Joel'
		mockParams.lastName = 'Hodgeson'
		def model = controller.register()
		assert model?.user
		def user = model.user
		assert user.hasErrors()
		assertEquals 'user.password.dontmatch', user.errors.password
    }
	
	void testRegistrationSuccess() {
		mockRequest.method = 'POST'
		mockDomain User
		mockParams.login = 'tomservo'
		mockParams.password = 'satelliteoflove'
		mockParams.confirm = 'satelliteoflove'
		mockParams.firstName = 'Tom'
		mockParams.lastName = 'Servo'
		def model = controller.register()
		assertNull model
		assertNotNull mockSession.user
		assertEquals 'store', redirectArgs.controller
	}
	
	void testLoginTooShort() {
		mockRequest.method = 'POST'
		mockDomain User
		mockParams.login = "joel"
		mockParams.password = 'mst3k'
		mockParams.confirm = 'mst3k'
		mockParams.firstName = 'Joel'
		mockParams.lastName = 'Hodgeson'
		def model = controller.register()
		assert model?.user
		def user = model.user
		assert user.hasErrors()
		assertEquals 'size', user.errors.login
	}
	
	void testRegistrationFailedEmptyLogin() {
		mockRequest.method = 'POST'
		mockDomain User
		mockParams.login = ''
		def model = controller.register()
		assertNull mockSession.user
		assert model
		def user = model.user
		assert user.hasErrors()
		assertEquals 'blank', user.errors.login
		assertEquals 'nullable', user.errors.password
		assertEquals 'nullable', user.errors.firstName
		assertEquals 'nullable', user.errors.lastName
	}
	
	void testLoginUserNotFound() {
		mockRequest.method = 'POST'
		mockDomain User
		MockUtils.prepareForConstraintsTests LoginCommand
		def cmd = new LoginCommand(login:'crowt', password:'letmein')
		cmd.validate()
		controller.login cmd
		assertTrue cmd.hasErrors()
		assertEquals 'user.not.found', cmd.errors.login
		assertEquals '/store/index', renderArgs.view
	}
	
	void testLoginPasswordInvalid() {
		mockRequest.method = 'POST'
		mockDomain User, [new User(login:'fred', password:'realpassword')]
		MockUtils.prepareForConstraintsTests LoginCommand
		def cmd = new LoginCommand(login:'fred', password:'letmein')
		cmd.validate()
		controller.login cmd
		assertTrue cmd.hasErrors()
		assertEquals 'user.password.invalid', cmd.errors.password
		assertEquals '/store/index', renderArgs.view
	}
	
	void testLoginSucess() {
		mockRequest.method = 'POST'
		mockDomain User, [new User(login:'fred', password:'letmein')]
		MockUtils.prepareForConstraintsTests LoginCommand
		def cmd = new LoginCommand(login:'fred', password:'letmein')
		cmd.validate()
		controller.login(cmd)
		assertFalse cmd.hasErrors()
		assertNotNull mockSession.user
		assertEquals 'store', redirectArgs.controller
	}
	
}
