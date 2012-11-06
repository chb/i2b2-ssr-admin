package edu.chip.i2b2ssr.admin.jobs

import grails.gsp.PageRenderer
import edu.chip.i2b2ssr.admin.data.Study
import org.apache.log4j.Logger
import edu.chip.i2b2ssr.admin.data.Machine

/**
 * This is a job that updates the roting table
 * if i2b2ssr.routingTableFile = "someFile"
 *
 * is set in the config file, runs once a minute
 */
class RoutingTableJob {
    static Logger log = Logger.getLogger(RoutingTableJob.class)
    def group = "MyGroup"

    static triggers = {
      simple name: 'routingTableJob', startDelay: 10000, repeatInterval: 60000
    }

    PageRenderer groovyPageRenderer
    def grailsApplication


    def execute() {

        if(grailsApplication.config.i2b2ssr.routingTableFile == null ){
            log.debug("Couldn't run routing table update, config not set")
            return
        }

        File routingTableFile = new File(grailsApplication.config.i2b2ssr.routingTableFile.toString())

        routingTableFile.withWriter { writer ->
            groovyPageRenderer.renderTo(view: "/api/routing_table_xml", model: [studies: Study.all, machines: Machine.all],writer )
        }
        log.debug("Updated Routing table")

    }



}


