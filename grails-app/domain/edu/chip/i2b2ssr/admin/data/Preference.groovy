package edu.chip.i2b2ssr.admin.data

class Preference {


    String i2b2OntCell = "http://carra-i2b2-production:8080/ont"
    String shrineCell = "https://carra-core:8081/shrine-cell"

    //the heartbeat must have a study
    Study heartBeatStudy



    static constraints = {
        heartBeatStudy nullable: true
    }
}
