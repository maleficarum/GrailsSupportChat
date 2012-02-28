var __SC_TO_MESSAGE__;
var __SC_LOGGED_IN__ = false;
var SUPPORTCHAT = {
	Host:"",
	Ajax: {
		sendMessage: function(from, message) {
			if(!__SC_LOGGED_IN__) {
				SUPPORTCHAT.Ajax.joinChat(from);
			}
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/sendMessage/",
					data: "spfrom=" + from + "&spmessage=" + message + "&spto=" + (__SC_TO_MESSAGE__ == null? "" : __SC_TO_MESSAGE__)
			}).done(function( msg ) {
			 	console.info(msg);
			});			
		}, joinChat : function(u) {
			$.ajax({
				type: "GET",
				url: SUPPORTCHAT.Host + "/maleficarum/joinChat/",
				data: "spuname=" + u
			}).done(function( msg ) {
				__SC_LOGGED_IN__ = true;
			 	console.info(msg + " u " + u);
				setInterval("SUPPORTCHAT.Ajax.fetchMessages('" + u + "')",5000);
			});
		}, disconnect: function(u) {
			$.ajax({
				type: "GET",
				url: SUPPORTCHAT.Host + "/maleficarum/unjoinChat/",
				data: "spuname=" + u
			}).done(function( msg ) {
			 	console.info(msg + " u " + u);
				setInterval("SUPPORTCHAT.Ajax.fetchMessages('" + u + "')",5000);
			});
		}, fetchMessages : function(u) {
			if(!__SC_LOGGED_IN__) {
				SUPPORTCHAT.Ajax.joinChat(u);
				return;
			}			
			$.ajax({
				type: "GET",
				url: SUPPORTCHAT.Host + "/maleficarum/fetchMessages/",
				data: "spuname=" + u
			}).done(function( json ) {
				var r = "";
				if(json.properties == null) {
					return;
				}
				if(__SC_TO_MESSAGE__ == null) {
					__SC_TO_MESSAGE__ = json.properties.to
				}
				if(json.properties.from == u || json.properties.to == u) {
					if(json.properties.messages.length == 0) {
						r += "[ " + json.properties.from + " ] logged in.\n";
					} else {
						$.each(json.properties.messages, function(i, val) { r += val + "\n"; });					
					}
					$('#scConversation').val(r);
		            $('#scConversation').scrollTop(
		                $('#scConversation')[0].scrollHeight - $('#scConversation').height()
		            );					
				}
			});
		}
	}
}

