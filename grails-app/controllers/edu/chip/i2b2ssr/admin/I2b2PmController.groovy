package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Preference
import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.exception.PermissionException

class I2b2PmController {
    AuthenticationService authService

    static allowedMethods = [getServices: 'POST']


    def getServices = {
        def xml = request.reader.text

        def node = new XmlSlurper().parseText(xml)

        String username = node.message_header.security.username.text()
        String password = node.message_header.security.password.text()

        QuerySession q = authService.authenticateWithSession(username, password)

        if (!q) {
            throw new PermissionException("couldn't auth for PM")
        }

        Preference p = Preference.all.first()

        String shrineCellUrl = p.shrineCell + "/rest/i2b2/"
        String ontCellUrl = p.i2b2OntCell + "/"




        render(contentType: "text/xml", view: "response.xml", model: [session: q, user: q.user, shrineCellUrl: shrineCellUrl, ontCellUrl: ontCellUrl])
    }


}
