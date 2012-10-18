package edu.chip.i2b2ssr.admin.data

class Machine {

    static def SHRINE_OK = "Good"
    static def MACHINE_AVAILABLE = "Available"
    static def MACHINE_BAD = "Bad"

    String name
    String certificate
    String realName
    Integer lastPatientCount = 0
    Long lastResponseTimeInMillis = -1
    URL url
    String status = MACHINE_BAD
    static hasMany = [studies: Study]
    static belongsTo = Study
    static constraints = {
        certificate nullable: true
        name unique: true
        lastPatientCount nullable: true
        lastResponseTimeInMillis nullable: true
        status(nullable: true, is: ([SHRINE_OK,
                MACHINE_AVAILABLE,
                MACHINE_BAD]))
    }

}
