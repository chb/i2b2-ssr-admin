package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.Study

class UserController {
    String menuName = "Edit Users"


    static allowedMethods = [list:'GET', show:'GET', delete: 'DELETE']

    def index = {
        redirect(action: "list", params: params) }

    def list = {
        [users: User.all]
    }

    def show = {
        [user: User.find(params.id)]
    }

    def edit = {
        [user: User.get(params.id),
         studies: Study.all]
    }

    def save = {
        User u;
        String message;
        if(params?.id){
            u = User.get(params.id)
            u.setProperties(params)
            message = "Updated user ${params.userName}"
        }
        else{
            u = new User(params)
            message = "Created user ${params.userName}"
        }


        //List<Study> = Study.
        if (!u.hasErrors() && u.save()){
            flash.message = message
            redirect(action: list)
        }
        else{
            flash.message = "Failure saving user ${params.userName}"
            redirect(action: create)
        }


    }

    def create ={
       [studies : Study.all]
    }

    def delete = {
        User u = User.get(params.id)
        if (u) {
            try {
                u.delete(flush: true)
                flash
                render("Successfully deleted")

            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "User {params.id} could not be deleted ${e.message}"
            }

        }

    }

}
