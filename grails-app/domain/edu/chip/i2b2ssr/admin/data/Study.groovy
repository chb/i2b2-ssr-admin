package edu.chip.i2b2ssr.admin.data

class Study {

    String studyName
    String studyDescription
    static hasMany = [machines: Machine, permission: Permission]
    static constraints = {
        studyName unique: true
        studyDescription nullable: true

    }


}
