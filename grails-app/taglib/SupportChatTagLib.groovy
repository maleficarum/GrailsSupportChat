
class SupportChatTagLib {

	static namespace = "mm"
	def jQueryVersion = "1.7.1"
	def jQueryUrl = "http://code.jquery.com/jquery-"
	
	def jquery = { attrs, body ->
		if(attrs?.version) {
			jQueryVersion = attrs.version
		}
		out << "<script src=\"${jQueryUrl}${jQueryVersion}.min.js\"></script>"
	}
	
	def supportChat = { attrs, body ->
		out << render(template:"/templates/chatWindow", plugin:"supportChat")
//	    out << body()
	}

}