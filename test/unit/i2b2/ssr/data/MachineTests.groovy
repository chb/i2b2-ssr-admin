package i2b2.ssr.data



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Machine)
class MachineTests {

    void testBasic() {
        Machine p = new Machine(name: "Dave", url: new URL("http://test.com"))
        assertEquals(p.name, "Dave")
        assertEquals("http://test.com", p.url.toString())
        p.save()
        assertEquals(1, Machine.all.size())
        assertEquals("Dave", Machine.get(1).name)
    }
}
