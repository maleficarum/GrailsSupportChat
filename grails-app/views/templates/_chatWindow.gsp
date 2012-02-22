<div id="mmsupportchatw" title="Support Chat">
	<form class="well">
			<p><textarea class="span3" name="scConversation" id="scConversation" rows="5" cols="1"></textarea></p>
			<p><input type="text" class="span3" name="mmsctext" placeholder="Type your message here ..." /></p>
			<p><button type="submit" class="btn" id="_sp_send_message_">Send</button></p>
	</form>
</div>

<script>
$(function() {
		$( "#mmsupportchatw" ).dialog({ position: 'bottom', closeOnEscape: false, width: 350, resizable: false });
		
		var _SP_ = SUPPORTCHAT;
		_SP_.Host = "${grailsApplication.config.grails.serverURL}";

		_SP_.Ajax.joinChat('${session['user'].id}');
		
		$('#_sp_send_message_').click(function(e) {
			e.preventDefault();
			_SP_.Ajax.sendMessage('${session['user'].id}','prueba')
		});
});
</script>