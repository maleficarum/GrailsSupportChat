
class SupportChatTagLib {

	static namespace = "mm"
	
	/**
	* This closure is used if you want to add our jquery version. If you want to provide
	* your own jquery version, not use this closure.
	*/
	def jquery = { attrs, body ->
		def url = grailsApplication.config.grails.serverURL		
		out << "<script src=\"${url}/js/jquery-1.7.1.min.js\"></script>"
	}
	
	/**
	* This closure is used if you want to add our jqueryui version. If you want to provide
	* your own jqueryui version, not use this closure.
	*/	
	def jqueryui = { attrs, body ->
		def url = grailsApplication.config.grails.serverURL		
		out << "<script src=\"${url}/js/jquery-ui-1.8.17.custom.min.js\"></script>"		
		out << "<link rel='stylesheet' href='${url}/css/jquery-ui-1.8.17.custom.css' />"			
	}
	
	/**
	* Creates the main chat window
	*/	
	def supportChat = { attrs, body ->
		def url = grailsApplication.config.grails.serverURL

		if(!attrs.twitter) {
			out << "<script src=\"${url}/js/bootstrap.min.js\"></script>"		
			out << "<link rel='stylesheet' href='${url}/css/bootstrap.min.css' />"		
			out << "<link rel='stylesheet' href='${url}/css/bootstrap-responsive.min.css' />"			
		}
		out << "<script src=\"${url}/js/sp.js\"></script>"	
		out << render(template:"/templates/chatWindow", plugin:"supportChat")
	}

}