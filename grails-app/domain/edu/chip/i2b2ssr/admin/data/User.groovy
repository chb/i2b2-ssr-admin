package edu.chip.i2b2ssr.admin.data

class User {

    String userName
    String realName
    String institutionName
    boolean isSystemUser = false
    boolean isAdmin = false

    static hasMany = [permissions: Permission, homesites: Machine, querySessions: QuerySession]



    static constraints = {
        userName unique: true
        realName nullable: true
        institutionName nullable: true
    }

    //This method makes the assumption that the backend user has
    static User getBackgroundJobUser(){
        return User.findByUserNameAndIsSystemUser(SYSTEM_USER,true)

    }

    static String SYSTEM_USER = "systemJobUser"
}
