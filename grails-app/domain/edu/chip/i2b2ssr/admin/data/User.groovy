package edu.chip.i2b2ssr.admin.data

class User {

    String userName
    String realName
    String institutionName
    boolean isAdmin = false

    static hasMany = [permissions: Permission, homesites: Machine, querySessions: QuerySession]



    static constraints = {
        userName unique: true
        realName nullable: true
        institutionName nullable: true
    }
}
