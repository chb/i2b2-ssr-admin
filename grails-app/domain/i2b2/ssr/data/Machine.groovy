package i2b2.ssr.data

class Machine {

    String name
    String certificate
    URL url
    static hasMany = [studies: Study]
    static belongsTo = Study
}
