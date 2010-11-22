
<%@ page import="tekdays.TekEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    

        <div class="nav">
            <!--  link to home -->
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <!--  link to list controller action -->
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <!-- link to create controller action -->
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        
  
        <div class="body">
            <h1>${fieldValue(bean:tekEventInstance, field:'name')}</h1>
            <!--  display flash error messages if any -->
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            
            <div class="dialog">
                <table>
                    <tbody>
                    
                    	<!-- CITY -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.location.label" default="Location:" /></td>
                            <td valign="top" class="value">
                            	${fieldValue(bean: tekEventInstance, field: "venue")}, ${fieldValue(bean: tekEventInstance, field: "city")}
                            </td>
                        </tr>
                    
                    	<!-- DESCRIPTION -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.description.label" default="Description" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tekEventInstance, field: "description")}</td>
                        </tr>

						<!-- VENUE -->                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.venue.label" default="Venue" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tekEventInstance, field: "venue")}</td>
                        </tr>
                    
                    	<!-- START DATE -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.startDate.label" default="Start Date" /></td>
                            <td valign="top" class="value"><g:formatDate date="${tekEventInstance?.startDate}" /></td>
                        </tr>
                    
                    	<!-- END DATE -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.endDate.label" default="End Date" /></td>
                            <td valign="top" class="value"><g:formatDate date="${tekEventInstance?.endDate}" /></td>
                        </tr>
                        
						<!-- ORGANIZER -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.organizer.label" default="Organizer" /></td>
                            <td valign="top" class="value">
                            	<g:link controller="tekUser" action="show" id="${tekEventInstance?.organizer?.id}">
                            		${tekEventInstance?.organizer?.encodeAsHTML()} <%-- -using encodeAsHTML() helps prevent CSS attacks --%>
                            	</g:link>
                            </td>
                        </tr>
                    
                    	<!-- VOLUNTEERS -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.volunteers.label" default="Volunteers" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${tekEventInstance.volunteers}" var="v">
                                    <li><g:link controller="tekUser" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                        </tr>
                    
                    	<!-- SPONSORSHIPS -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.sponsorships.label" default="Sponsorships" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
	                                <g:each in="${tekEventInstance.sponsorships}" var="s">
	                                    <li><g:link controller="sponsorship" action="show" id="${s.id}">${s.sponsor?.encodeAsHTML()}</g:link></li>
	                                </g:each>
                                </ul>
                            </td>
                        </tr>
                    
                    	<!-- TASKS -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.tasks.label" default="Tasks" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${tekEventInstance.tasks}" var="t">
                                    <li><g:link controller="task" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                        </tr>
                    
                    	<!-- MESSAGES -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.messages.label" default="Messages" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                            	<g:link controller="message" action="list" id="${tekEventInstance.id}">View Messages</g:link>
                            </td>
                        </tr>
                    
                    	<!-- RESPONDENTS -->
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tekEvent.respondents.label" default="Respondents" /></td>
                            <td valign="top" class="value">
                            	<%-- ${fieldValue(bean: tekEventInstance, field: "respondents")} --%>
                            	<ul>
                            		<g:each in="${tekEventInstance?.respondents}" var="respondent"><li>${respondent.encodeAsHTML()}</li></g:each>
                            	</ul>
                            </td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            
            <!--  Edit and Delete -->
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${tekEventInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
