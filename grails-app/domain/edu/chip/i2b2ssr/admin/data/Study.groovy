package edu.chip.i2b2ssr.admin.data

class Study {

    String studyName
    String studyDescription
    static hasMany = [machines: Machine, users : User]
    static belongsTo = User
    static constraints = {
        studyName unique: true
        studyDescription nullable: true

    }
}
