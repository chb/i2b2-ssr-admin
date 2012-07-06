package edu.chip.i2b2ssr.admin.data

class Machine {

    String name
    String certificate
    URL url
    static hasMany = [studies: Study]
    static belongsTo = Study
    static constraints = {
                           certificate(nullable: true)
                           name(unique: true)
                        }

}
