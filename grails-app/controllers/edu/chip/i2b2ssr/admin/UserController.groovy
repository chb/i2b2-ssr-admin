package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.data.Study
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.service.AuthenticationService

class UserController {
  String menuName = "Edit Users"

  AuthenticationService authService;

  static allowedMethods = [list: 'GET', show: 'GET', delete: 'DELETE', login: 'GET', auth: 'POST']

  def auth = {
    def user = params.login
    def password = params.password

    User u = authService.authenticate(user,password)
    if(u && u.isAdmin){
      session.user = u
      redirect(uri: "/")
    }
    else{
      flash["message"] = "Authentication failed"
      redirect(action: login)
    }
  }

  def login = {

  }

  def logout ={
    session.user = null
    redirect(action: "login")

  }


  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    def users = User.where{userName != User.SYSTEM_USER}
    [count: users.count(),
     users: users.list(params)]
  }

  def show = {
    [user: User.find(params.id)]
  }

  def edit = {
    User u = User.get(params.id)
    [user: u, machines: Machine.all, studies: Study.all]
  }

  def save = {
    User u;
    String message;
    if(params?.id) {
      u = User.get(params.id)
      u.setProperties(params)
      message = "Updated user ${params.userName}"
    }
    else {
      u = new User(params)
      message = "Created user ${params.userName}"
    }

    u.isAdmin = params.isAdmin ? true : false

    //List<Study> = Study.
    if(!u.hasErrors() && u.save()) {
      flash.message = message
      redirect(action: list)
    }
    else {
      flash.message = "Failure saving user ${params.userName}"
      redirect(action: create)
    }


  }

  def create = {
    [studies: Study.all,
            machines: Machine.all]
  }

  def delete = {
    User u = User.get(params.id)
    if(u) {
      try {
        u.delete(flush: true)
        flash
        render("Successfully deleted")

      }
      catch(org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "User {params.id} could not be deleted ${e.message}"
      }

    }

  }

}
