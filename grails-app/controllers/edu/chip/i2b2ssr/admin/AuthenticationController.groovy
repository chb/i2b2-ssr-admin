package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.service.AuthenticationService

class AuthenticationController {

  AuthenticationService service

  def callback = {
    String username = params.username
    String sessionKey = params.sessionkey

    if(service.checkSessionKey(username, sessionKey)){
      render("ok")
    }
    else{
      render(text: "bad SessionKey", status: 403)
    }
  }
}
