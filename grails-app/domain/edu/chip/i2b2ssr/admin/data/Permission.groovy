package edu.chip.i2b2ssr.admin.data

class Permission {
    User user
    Study study
    boolean allowPdo = false
    boolean allowSiteIdentify = false

    static belongsTo = [User]
    static constraints = {
        id composite:['id','user','study']
    }



}
