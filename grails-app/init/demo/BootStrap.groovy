package demo

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    def init = { servletContext ->

        final boolean flush = true
        final boolean failOnError = true

        def sherlock = new User(username: 'sherlock', password: 'elementary')
        sherlock.save(flush: flush, failOnError: failOnError)

        def watson = new User(username: 'watson', password: 'houndsofbaskerville')
        watson.save(flush: flush, failOnError: failOnError)

        def detectives =  new RoleGroup(name: 'Detectives')
        detectives.save(flush: flush, failOnError: failOnError)

        def detectiveRole = new Role(authority: 'ROLE_ADMIN')
        detectiveRole.save(flush: flush, failOnError: true)

        new RoleGroupRole(roleGroup: detectives, role: detectiveRole).save(flush: flush, failOnError: failOnError)

        new UserRoleGroup(user: sherlock, roleGroup: detectives).save(flush: flush, failOnError: failOnError)
        new UserRoleGroup(user: watson, roleGroup: detectives).save(flush: flush, failOnError: failOnError)
    }
    def destroy = {
    }
}
