package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.data.Study

class MachineController {
  String menuName = "Edit Machines"

  static allowedMethods = [list: 'GET', show: 'GET', delete: 'DELETE']


  def index() {
    redirect(action: list)
  }


  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [count: Machine.count,
     machines: Machine.list(params)]
  }

  def edit = {
    [machine: Machine.get(params.id)]
  }

  def save = {
    Machine m;
    String message;
    if(params?.id) {
      m = Machine.get(params.id)
      m.setProperties(params)
      message = "Updated machine ${params.name}"
    }
    else {
      m = new Machine(params)
      message = "Created machine ${params.name}"
    }

    if(!m.hasErrors() && m.save()) {
      flash.message = message
      redirect(action: list)
    }
    else {
      flash.message = "Failure saving machine ${params.name}"
      redirect(action: create)
    }


  }

  def create = {
  }

  def delete = {
    Machine m = Machine.get(params.id)
    if(m) {
      try {
        for(Study s : m.studies) {
          s.removeFromMachines(m)
        }
        m.delete(flush: true)
        render("Successfully deleted")

      }
      catch(org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "Machine {params.id} could not be deleted ${e.message}"
      }

    }

  }


}
