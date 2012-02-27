<div id="mmsupportchatw" title="${message(code: 'sp.main.title')}">
	
	<form class="well">
			<p><textarea class="span3" name="scConversation" id="scConversation" rows="5" cols="1"></textarea></p>
			<p><input type="text" class="span3" id="mmsctext" name="mmsctext" placeholder="${message(code: 'sp.input.message.placeholder')}" /></p>
			<p><button type="submit" class="btn" id="_sp_send_message_">${message(code: 'sp.input.message.placeholder')}</button></p>
	</form>
</div>

<script>
$(function() {
		$( "#mmsupportchatw" ).dialog({ position: 'bottom', closeOnEscape: false, width: 350, resizable: false, 
			close: function(event, ui) { 
				SUPPORTCHAT.Ajax.disconnect('${session['user'].id}'); 
			} 
		});
		
		$(window).unload(function () {  
			SUPPORTCHAT.Ajax.disconnect('${session['user'].id}'); 
		});
		
		var _SP_ = SUPPORTCHAT;
		_SP_.Host = "${grailsApplication.config.grails.serverURL}";

		_SP_.Ajax.joinChat('${session['user'].id}');
		
		$('#_sp_send_message_').click(function(e) {
			e.preventDefault();
			_SP_.Ajax.sendMessage('${session['user'].id}', $('#mmsctext').val());
			$('#mmsctext').val("");
		});
});
</script>