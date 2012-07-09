package edu.chip.i2b2ssr.admin

class UserController {
    String menuName = "Edit Users"

    def index = { redirect(action: list, params: params) }

    def list = {

    }
}
