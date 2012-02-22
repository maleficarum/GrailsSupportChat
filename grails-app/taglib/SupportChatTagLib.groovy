
class SupportChatTagLib {

	static namespace = "mm"
	
	/**
	* This closure is used if you want to add our jquery version. If you want to provide
	* your own jquery version, not use this closure.
	*/
	def jquery = { attrs, body ->
		out << "<script src=\"js/jquery-1.7.1.min.js\"></script>"
	}
	
	/**
	* This closure is used if you want to add our jqueryui version. If you want to provide
	* your own jqueryui version, not use this closure.
	*/	
	def jqueryui = { attrs, body ->
		out << "<script src=\"js/jquery-ui-1.8.17.custom.min.js\"></script>"		
		out << "<link rel='stylesheet' href='css/jquery-ui-1.8.17.custom.css' />"			
	}
	
	/**
	* Creates the main chat window
	*/	
	def supportChat = { attrs, body ->
		out << "<script src=\"js/bootstrap.min.js\"></script>"		
		out << "<link rel='stylesheet' href='css/bootstrap.min.css' />"		
		out << "<link rel='stylesheet' href='css/bootstrap-responsive.min.css' />"		
		out << "<script src=\"js/sp.js\"></script>"	
		out << render(template:"/templates/chatWindow", plugin:"supportChat")
	}

}