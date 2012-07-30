package edu.chip.i2b2ssr.admin.data

class User {

    String userName
    String realName
    String institutionName

    static hasMany = [permissions: Permission, homesites: Machine]



    static constraints = {
        userName unique: true
        realName nullable: true
        institutionName nullable: true
    }
}
