package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.data.Permission
import edu.chip.i2b2ssr.admin.data.Study

/**
 * This class provides an API for other applications such as SHRINE
 */
class ApiController {

    AuthenticationService service

    /**
     * This is the SHRINE sessionKey callback
     *
     */
    def authentication_callback = {
        String username = params.username
        String project = params.project
        String sessionKey = params.sessionKey

        if (service.checkSessionKey(username, sessionKey)) {
            Permission p = service.findPermission(username, project)
            if (p == null) {
                render(text: "No Permission", status: 403)
            }
            else {
                render(view: "authentication_callback_xml", model: [username: username,
                        project: project,
                        allowPdo: p.allowPdo,
                        allowSiteIdentity: p.allowSiteIdentify,
                        homeSites: p.user.homesites*.name
                        ])
            }
        }
        else {
            render(text: "Bad SessionKey", status: 403)
        }
    }

    //no security, open call
    def routing_table = {
      render(view: "routing_table_xml", model: [studies: Study.all])
    }


}
