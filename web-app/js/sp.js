var SUPPORTCHAT = {
	Host:"",
	Ajax: {
		sendMessage: function(username, message) {
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/sendMessage/",
					data: "spuname=" + username + "&spmessage=" + message
			}).done(function( msg ) {
			 	console.info(msg);
			});			
		}, joinChat : function(u) {
			$.ajax({
					type: "GET",
					url: SUPPORTCHAT.Host + "/maleficarum/joinChat/",
					data: "spuname=" + u
			}).done(function( msg ) {
			 	console.info(msg);
			});			
		}
	}
}

