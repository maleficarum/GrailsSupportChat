<div id="mmsupportchatw" title="Support Chat">
	<form class="well">
	        <label>Conversation</label>
			<textarea class="span3" id="textarea" rows="5" cols="1"></textarea>
	        <label>${session['supportChatUser']} your message</label>
			<input type="text" class="span3" name="mmsctext" />
	        <button type="submit" class="btn" id="_sp_send_message_">Send</button>
	</form>
</div>

<script>
$(function() {
		$( "#mmsupportchatw" ).dialog({ position: 'bottom', closeOnEscape: false, width: 350, resizable: false });
		
		var _SP_ = SUPPORTCHAT;
		$('#_sp_send_message_').click(function(e) {
			e.preventDefault();
			_SP_.Ajax.sendMessage('oscar','prueba')
		});
});
</script>