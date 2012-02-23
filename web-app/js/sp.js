var SUPPORTCHAT = {
	Host:"",
	Ajax: {
		sendMessage: function(from, to, message) {
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/sendMessage/",
					data: "spfrom=" + from + "&spmessage=" + message + "&spto="
			}).done(function( msg ) {
			 	console.info(msg);
			});			
		}, joinChat : function(u) {
			console.info("1 " + u)
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/joinChat/",
					data: "spuname=" + u
			}).done(function( msg ) {
			 	console.info(msg + " u " + u);
				setInterval("SUPPORTCHAT.Ajax.fetchMessages('" + u + "')",5000);
			});
		}, fetchMessages : function(u) {
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/fetchMessages/",
					data: "spuname=" + u
			}).done(function( json ) {
				var r = "";
				
				if(json.properties.from == u || json.properties.to == u) {
					if(json.properties.messages.length == 0) {
						r += "[ " + json.properties.from + " ] logged in.\n";
					} else {
						$.each(json.properties.messages, function(i, val) { r += val + "\n"; });					
					}
					$('#scConversation').val(r);					
				}
			});
		}
	}
}

