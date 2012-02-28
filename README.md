# Grails Support Chat README

## Usage

To embbed a support chat window on your grails application you need to modify some files:

* Config.groovy

Add de following snippet to your config file

##### Config.groovy

supportChat {
	supportEntity = com.enterprise.Admin.class
	userEntity = com.enterprise.SingleUser.class
}

**Note:** If your application not uses logged clients/users, use an Expando instance in every client session.

