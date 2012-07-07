package edu.chip.i2b2ssr.admin.data

class Machine {

    static def MACHINE_GOOD ="Good"
    static def MACHINE_UNKNOWN = "Unknown"
    static def MACHINE_BAD = "Bad"

    String name
    String certificate
    String realName
    URL url
    String status = MACHINE_BAD
    static hasMany = [studies: Study]
    static belongsTo = Study
    static constraints = {
                           certificate nullable: true
                           name unique: true
                           status(nullable: true,  is:([MACHINE_GOOD,
                                                        MACHINE_UNKNOWN,
                                                        MACHINE_BAD]))
                        }

}
