package edu.chip.i2b2ssr.admin.jobs

/**
 * This is a job to run the SHRINE heartbeat across all the nodes
 *
 */
class HeartbeatJob {
    static triggers = {
      //one once per hour
      simple name: 'HeartBeatJob', startDelay: 60000, repeatInterval: 3600000
    }
    def group = "MyGroup"
    def statusService

    def execute() {
        log.debug("Running heartbeat")
        statusService.runHeartBeat()
        log.info("Cleaning up old heartbeat data (> 30 days)")
        statusService.cleanupOldStatus()
    }


}
