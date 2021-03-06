package mx.maleficarum.supportchat

import grails.converters.JSON
import org.slf4j.LoggerFactory

/**
* Controller to process request's and response's
*
* @author Oscar Ivan Hernandez V. [ http://github.com/maleficarum ]
*/
class MaleficarumController {
	
	def servletContext
	def log = LoggerFactory.getLogger(getClass())
	
	def unjoinChat() {
		def map
		
		if(session['user'].isScAdmin()) {
			map = servletContext.getAttribute('scAdminSessions')
		} else {
			map = servletContext.getAttribute('scUsersSessions')			
		}
		
		map?.remove(params.spuname)	
		flash.message = "ok"	
		render flash as JSON
	}

	def joinChat() {
		def e = new Expando()
		e.from = session['user'].id
		e.to = null
		e.messages = []		

		if(session['user'].isScAdmin()) {
			def map = servletContext.getAttribute('scAdminSessions') 
			if(!map.get(session['user'].id)) {
				map.put(session['user'].id, e)
				log.info("Join user admin ${session['user'].id} to chat")
				flash.message = "ok"				
			} else {
				log.info("The user admin ${session['user'].id} is in chat now")
				flash.message = "ok"								
			}
		} else {
			def map = servletContext.getAttribute('scUsersSessions')
			if(!map.get(session['user'].id)) {
				map.put(session['user'].id, e)
				log.info("Join user ${session['user'].id} to chat")				
				flash.message = "ok"								
			} else {
				log.info("The user ${session['user'].id} is in chat now")				
				flash.message = "ok"								
			}
		}
		render flash as JSON
	}

    def sendMessage() {
		
		if(!(params.spfrom && params.spmessage)) {
			flash.error = "No username or message given."
		} else {
			if(session['user'].isScAdmin()) {
				def vsource = servletContext.getAttribute('scAdminSessions').get(params.spfrom)
				def vdest = servletContext.getAttribute('scUsersSessions').get(params.spto)				
				//If to, then send broadcast for all users
				if(params.spto) {
					//PARAMS [spfrom:admin, spmessage:sera, spto:304280490000, action:sendMessage, controller:maleficarum]
					//Direct message					
					vsource.messages << message(code:'sp.controller.message', args:[params.spfrom, params.spmessage])
					vdest.messages << message(code:'sp.controller.message', args:[params.spfrom, params.spmessage])
				}

			} else {
				//The message was delivered to some moderator?
				def sended = false
				//Iterate for all moderators and send a message to their queue
				servletContext.getAttribute('scAdminSessions').each { k,v ->
					//The moderator is bussy?
					if(!v.to) {
						//This is inverted cause the 'to' of admin is 'from' in user messages
						v.to = params.spfrom
						v.messages << message(code:'sp.controller.message', args:[params.spfrom, params.spmessage])
						log.info("Adding message to ${v}")
						sended = true						
					} else if(v.to == params.spfrom) {
						v.messages << message(code:'sp.controller.message', args:[params.spfrom, params.spmessage])
						log.info("Adding direct message message to ${v}")						
						sended = true						
					}
				}
				//Add message to user queue
				def v = servletContext.getAttribute('scUsersSessions').get(params.spfrom)
				v.messages << message(code:'sp.controller.message', args:[params.spfrom, params.spmessage])
				
				if(!sended) {
					v.messages << "There's no moderators available."
				}
				
				flash.message = "ok"
			}			
		}
		render flash as JSON
	}
	
	def fetchMessages() {
		def messages
		if(session['user'].isScAdmin()) {
			messages = servletContext.getAttribute('scAdminSessions').get(params.spuname)			
		} else {
			messages = servletContext.getAttribute('scUsersSessions').get(params.spuname)						
		}
		render messages as JSON
	}
}
