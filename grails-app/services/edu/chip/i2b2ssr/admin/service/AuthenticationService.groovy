package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.User

class AuthenticationService {



    def User authenticate(String username, String password) {
        User u = User.findByUserName(username)


    }
}
