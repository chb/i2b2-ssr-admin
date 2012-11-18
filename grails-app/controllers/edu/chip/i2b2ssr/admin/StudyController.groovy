package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.data.Study

class StudyController {
    String menuName = "Studies"


    static allowedMethods = [list: 'GET', show: 'GET', delete: 'DELETE']

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        [studies: Study.all]
    }

    def show = {
        [study: Study.find(params.id)]
    }

    def save = {


        Study s;
        String message
        if (params?.id) {
            s = Study.get(params.id)
            s.setProperties(params)
            message = "Updated study ${params.studyName}"

        }
        else {
            s = new Study(params)
            message = "Created study ${params.studyName}"

        }
        if (!s.hasErrors() && s.save()) {
            flash.message = message
            redirect(action: list)
        }
        else {
            flash.message = "Failure to create or update study ${params.studyName}"
            redirect(action: create)
        }


    }

    def create = {
        [machines : Machine.all]
    }

    def edit = {
        [study: Study.get(params.id),
         machines : Machine.all]
    }


    def delete = {
        def s = Study.get(params.id)
        if (s) {
            try {
                s.delete(flush: true)
                flash
                render("Successfully deleted")

            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "User {params.id} could not be deleted ${e.message}"
            }

        }

    }
}
