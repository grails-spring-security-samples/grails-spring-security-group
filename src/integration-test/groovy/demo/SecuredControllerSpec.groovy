package demo

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

@Rollback
@Integration
class SecuredControllerSpec extends GebSpec {

    def setup() {
        browser.baseUrl = "http://localhost:${serverPort}/"
    }

    def "test login as sherlock, sherlock belongs to detective groups. All detectives have the role ADMIN"() {
        when:
        to SecuredPage

        then:
        at LoginPage

        when:
        login('sherlock', 'elementary')

        then:
        browser.driver.pageSource.contains 'you have ROLE_ADMIN'

        and: 'User has not role assigned to him directly'
        UserRole.count() == 0
    }

    def "test login as watson, watson belongs to detective groups. All detectives have the role ADMIN"() {
        when:
        to SecuredPage

        then:
        at LoginPage

        when:
        login('watson', 'houndsofbaskerville')

        then:
        browser.driver.pageSource.contains 'you have ROLE_ADMIN'

        and: 'User has not role assigned to him directly'
        UserRole.count() == 0
    }
}

