package edu.chip.i2b2ssr.admin.data



import grails.test.mixin.*
import grails.converters.JSON

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Permission)
@Mock([User, Study])
class PermissionTests {

    void testPermissions() {
        //Create a test study with no machines
        Study s = new Study(studyName: "test", studyDescription: "test")
        s.save(failOnError: true)

        //Test basic persistence
        User u = new User(userName: "Dave", realName: "Dave Ortiz", institutionName: "test")
        u.save(failOnError: true, flush: true)

        Permission p = new Permission(user: u, allowPdo: true,allowSiteIdentify: true,study: s)
        p.save(failOnError: true)
        u.addToPermissions(p)

        u = User.get(1)


      assertEquals(true, u.permissions.contains(p))
      assertEquals(true, p.study.equals(s))
    }
}
