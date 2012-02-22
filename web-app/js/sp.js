var SUPPORTCHAT = {
	Ajax: {
		sendMessage: function(username, message) {
			$.ajax({
					type: "GET",
					url: "/SP/maleficarum/sendMessage/",
					data: "spuname=" + username + "&spmessage=" + message
			}).done(function( msg ) {
			  alert( "Data Saved: " + msg );
			});			
		}
	}
}