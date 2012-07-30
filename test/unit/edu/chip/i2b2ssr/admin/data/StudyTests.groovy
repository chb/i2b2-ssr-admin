package edu.chip.i2b2ssr.admin.data



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Study)
class StudyTests {

    void testAddBasicStudy() {
        def s = new Study(studyName: "TestStudy", studyDescription: "This is a test Study")
        s.addToMachines(new Machine(name: "TestMachine", url: new URL("http://urlofmachine.come")))
        s.save()

        assertEquals(1, Study.all.size())
        assertEquals(1, Study.get(1).machines.size())
    }

    /**
     * We want to make sure that if a machine is part of more than 1 study,
     * we don't delete the Machine as well...
     */
    void testDontDeleteMachineIfPartOfStudy() {
        Machine m1 = new Machine(name: "Test1", realName: "test", url: new URL("http://test1.com"))

        Study s1 = new Study(studyName: "TestStudy1", studyDescription: "Test1")

        s1.addToMachines(m1)
        s1.save()
        Study s2 = new Study(studyName: "TestStudy2", studyDescription: "Test1")
        s2.addToMachines(s1.getMachines().asList().get(0))

        s2.save()

        assertEquals(1, Study.get(1).machines.size())
        assertEquals(1, Study.get(2).machines.size())

        Study.get(1).delete()
        assertEquals(1, Study.get(2).machines.size())

    }
}
