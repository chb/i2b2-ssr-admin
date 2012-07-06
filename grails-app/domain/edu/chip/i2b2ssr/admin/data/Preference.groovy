package edu.chip.i2b2ssr.admin.data

class Preference {
    static String LDAP_ADDRESS = "ldapAddress"
    static String LDAP_BASE = "ldapBase"

    String preference
    String value

    static constraints = {
        preference inList: [LDAP_ADDRESS, LDAP_BASE]

    }
}
