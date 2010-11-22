<%-- This is a template that will get inserted into other gsp documents --%>

<div>
	<table>
		<%-- SUBJECT --%>
		<tr class="prop">
			<td valign="top" class="name">Subject:</td>
			<td valign="top" class="value">${fieldValue(bean:messageInstance, field:'subject')}</td>
		</tr>
		<%-- CONTENT --%>
		<tr class="prop">
			<td valign="top" class="name">Content:</td>
			<td valign="top" class="value">${fieldValue(bean:messageInstance, field:'content')}</td>
		</tr>
		<%-- AUTHOR --%>
		<tr class="prop">
			<td valign="top" class="name">Author:</td>
			<td valign="top" class="value">
				<g:link controller="tekuser" action="show" id="${messageInstance?.author?.id}">
					${messageInstance?.author?.encodeAsHTML()}
				</g:link>
			</td>
		</tr>
	</table>
	<div class="buttons">
		<span class="menuButton">
			<g:link class="create" action="reply" id="${messageInstance?.id}">Reply</g:link>
		</span>
	</div>
</div>