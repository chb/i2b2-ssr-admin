package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.Machine

class MachineService {

    def getAll() {
        Machine.all
    }

    def getAllAndUpdateStatus(){
        def machList = Machine.all
        for(machine in machList){
            //update value
        }
        return machList
    }
}
