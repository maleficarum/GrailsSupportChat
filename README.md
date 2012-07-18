# Grails Support Chat README

## Requirements

This plugin includes (if you want) all the needed resources to work :

<ul>
	<li>jquery 1.7.1</li>
	<li>jquery ui 1.8.18</li>
	<li>twitter bootstrap 2.0</li>
</ul>

... but, you can include your global resources.

## Usage
First, you need to define mavenRepo on config/BuildConfig.groovy in repositories section:

<pre>
mavenRepo "https://raw.github.com/maleficarum/mavenrepo/master/releases"
</pre>

Then, install plugin

<pre>
	grails install-plugin https://raw.github.com/maleficarum/mavenrepo/master/releases/mx/com/maleficarum/grails-support-chat/0.1/grails-support-chat-0.1.zip
</pre>

To embbed a support chat window on your grails application you need to modify some files:

##### Config.groovy

<pre>
	supportChat {
		supportEntity = com.enterprise.Admin.class
		userEntity = com.enterprise.SingleUser.class
	}
</pre>

<p><b>supportEntity</b> : This is the entity that represents a logged valid user as moderator.</p>
<p><b>userEntity</b> : This is the common user in the application.</p>

**Note:** If your application not uses logged clients/users, use an Expando instance in every client session.

##### Index.gsp (or in the target view)

<pre>
&lt;input type="button" id="openChat" /&gt;			(1)
&lt;mm:jquery /&gt; 						(2)
&lt;mm:jqueryui /&gt; 						(3)
&lt;mm:supportChat /&gt; 					(4)

&lt;srcipt&gt;
	$('#openChat').click(function(e) { 			
		$( "#mmsupportchatw" ).dialog( "open" ); 	(5)
	});
&lt;/srcipt&gt;
</pre>

<ol>
	<li>Add a button to launch the chat</li>
	<li>If you include directly jquery.js, omit this sentence.</li>
	<li>If you include directly jqueryui.js (and other neede resources, as templates, omit this sentence)</li>
	<li>Include main chat support window in your view.</li>
	<li>Add a listener for the previous button to fire open event in chat window</li>
</ol>

##### MainController.groovy

<pre>
	def index() {
		//If user is admin user, or moderator, then
		def user = new Admin()
		user.setScAdmin(true)
		//If user is not an admin, or not a moderator, then
		user.setScAdmin(false)
	}
</pre>

**Note:** The plugin add the method setScAdmin(boolean) in metaclass for the configured classes in the first step.
