package i2b2.ssr.data

class User {

    String userName
    String realName
    static hasMany = [studies: Study]


    static constraints = {
        userName unique: true
        realName nullable: true
    }
}
