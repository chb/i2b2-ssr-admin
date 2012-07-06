package i2b2.ssr.data

class Study {

    String studyName
    static hasMany = [machines: Machine, users : User]
    static belongsTo = User
    static constraints = {

    }
}
