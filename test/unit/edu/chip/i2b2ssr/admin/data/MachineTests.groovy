package edu.chip.i2b2ssr.admin.data

import grails.test.mixin.*
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Machine)
@Mock([Study, Machine])
class MachineTests {

  @Test
  void testCreateBasicMachine() {
    Calendar c1 = Calendar.getInstance()
    Status s1 = new Status(timeStamp: c1.getTime(), numberOfPatients: 1, responseTimeInMillis: 10)
    c1.add(Calendar.HOUR, 1)
    Status s2 = new Status(timeStamp: c1.getTime(), numberOfPatients: 2, responseTimeInMillis: 20)
    c1.add(Calendar.HOUR, 1)
    Status s3 = new Status(timeStamp: c1.getTime(), numberOfPatients: 3, responseTimeInMillis: 30)

    Machine p = new Machine(name: "Dave", realName: "test", url: new URL("http://test.com"))
    p.addToStatuses(s1)
    p.addToStatuses(s2)
    p.addToStatuses(s3)
    assertEquals(p.name, "Dave")
    assertEquals("http://test.com", p.url.toString())
    p.save(failOnError: true)
    assertEquals(1, Machine.all.size())
    assertEquals("Dave", Machine.get(1).name)
    assertEquals(p.statuses.size(), 3)

    assertEquals(p.latestStatus(), s3)

  }
}
