package mx.maleficarum.supportchat

import grails.converters.JSON

/**
* Controller to process request's and response's
*
* @author Oscar Ivan Hernandez V. [ http://github.com/maleficarum ]
*/
class MaleficarumController {

    def sendMessage() {
		render params as JSON
	}
}
