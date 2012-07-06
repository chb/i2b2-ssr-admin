package edu.chip.i2b2ssr.admin.data

class User {

    String userName
    String realName
    static hasMany = [studies: Study]

    static constraints = {
        userName unique: true
        realName nullable: true
    }
}
