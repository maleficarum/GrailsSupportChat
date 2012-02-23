class SupportChatGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Support Chat Plugin" // Headline display name of the plugin
    def author = "Oscar Ivan Hernandez Ventura"
    def authorEmail = "oscar@angellore.com.mx"
    def description = '''\
This plugin provides to any grails application a support chat to help users, getting communication with any authenticated user allowed to give support.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/support-chat"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "LGPL"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "maleficarum", url: "http://github.com/maleficarum" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Oscar I. Hernandez", email: "oscar@angellore.com.mx" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
		//Add a method to know if loged user is admin
		application.config.supportChat.supportEntity.metaClass.scadmin = true
		application.config.supportChat.supportEntity.metaClass.isScAdmin = { scadmin }
		application.config.supportChat.supportEntity.metaClass.setScAdmin = { v -> scadmin = v  }
		
		application.config.supportChat.userEntity.metaClass.scadmin = false
		application.config.supportChat.userEntity.metaClass.isScAdmin = { scadmin }
		application.config.supportChat.userEntity.metaClass.setScAdmin = { v -> scadmin = v  }		
    }

    def doWithApplicationContext = { applicationContext ->
		applicationContext.getServletContext().setAttribute("scAdminSessions", new java.util.concurrent.ConcurrentHashMap<String, Object>())
		applicationContext.getServletContext().setAttribute("scUsersSessions", new java.util.concurrent.ConcurrentHashMap<String, Object>())		
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
