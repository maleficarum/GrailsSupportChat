package mx.maleficarum.supportchat

import grails.converters.JSON

/**
* Controller to process request's and response's
*
* @author Oscar Ivan Hernandez V. [ http://github.com/maleficarum ]
*/
class MaleficarumController {
	
	def servletContext

	def joinChat() {
		def e = new Expando()
		e.from = session['user'].id
		e.to = null
		e.messages = []		
		if(session['user'].isScAdmin()) {
			servletContext.getAttribute('scAdminSessions').put(session['user'].id, e)
		} else {
			servletContext.getAttribute('scUsersSessions').put(session['user'].id, e)
		}
		render session['user'] as JSON
	}

    def sendMessage() {
		if(!(params.spuname && params.spmessage)) {
			flash.error = "No username or message given."
		} else {
			if(session['user'].isScAdmin()) {
				servletContext.getAttribute('scAdminSessions').get(params.spuname)
			} else {
				//Iterate for all moderators and send a message to their queue
				servletContext.getAttribute('scAdminSessions').each { k,v ->
					//The moderator is bussy?
					if(v.to) {
						v.to = params.spuname
						v.messages << params.spmessage						
					}
				}
				flash.message = "ok"
			}			
		}
		render flash as JSON
	}
}
