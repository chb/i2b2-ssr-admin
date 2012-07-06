package i2b2.ssr.data

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
