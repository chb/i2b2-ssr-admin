package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.data.Permission
import edu.chip.i2b2ssr.admin.data.Study
import edu.chip.i2b2ssr.admin.data.Machine

/**
 * This class provides an API for other applications such as SHRINE
 */
class ApiController {

  AuthenticationService authService;

  /**
   * This is the SHRINE sessionKey callback
   *
   */
  def authentication_callback = {
    String username = params.username
    String project = params.project
    String sessionKey = params.sessionKey

    if(authService.checkSessionKey(username, sessionKey)) {
      Permission p = authService.findPermission(username, project)
      if(p == null) {
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

  //no security, open call legacy from old CARRAnet OLS service
  def machine_list_legacy = {
    render(view: "machine_list_legacy_xml", model: [machines: Machine.all])
  }


}
