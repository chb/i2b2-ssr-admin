package edu.chip.i2b2ssr.admin.data



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    void testSomething() {
        User u = new User(userName: "Dave", realName: "Dave Ortiz")
        u.save()
        assertEquals(1,User.all.size())
    }
}
