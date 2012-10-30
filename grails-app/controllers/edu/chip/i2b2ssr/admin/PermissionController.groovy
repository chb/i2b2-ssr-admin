package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Permission
import edu.chip.i2b2ssr.admin.data.Study
import edu.chip.i2b2ssr.admin.data.User

/**
 * Controller for the Permission editor
 */
class PermissionController {


  def create = {
    List<Study> studies = new ArrayList<Study>()
    User u = User.get(params.userId)
    studies.addAll(studiesWithNoPermissions(params.userId))
    [user: u, study: Study.get(params.studyid), availableStudies: studies]
  }

  def edit = {
    Permission p = Permission.get(params.permissionId)
    [user: p.user, study: p.study, permission: p]
  }


  private Set<Study> studiesWithNoPermissions(String userId) {
    User u = User.get(userId)
    Set<Study> studies = new HashSet<Study>()
    studies.addAll(Study.all)

    if(u) {
      Collection<Study> addedStudies = User.get(userId)?.permissions*.study
      studies.removeAll(addedStudies)
    }
    return studies


  }

  def save = {
    User u = User.get(params.userId)
    Study s = Study.get(params.studyId)


    Permission p = Permission.get(params.permissionId)

    if(p == null) {
      p = new Permission()

      u.addToPermissions(p)
      s.addToPermission(p)
    }

    p.setProperties(allowPdo: params.allowPdo ? true : false,
            allowSiteIdentify: params.allowSiteIdentify ? true : false)




    if(u != null && s && !p.hasErrors()) {
      flash["message"] = "Saved Permission"
      p.save(failOnError: true)
      redirect(controller: "User", action: "list")
    }
    else {
      flash["message"] = "Error saving permission, please try again"
      redirect(controller: "Permission", action: "create", params: params)
    }
  }

  def delete = {
    Permission p = Permission.get(params.permissionId)
    if(p) {
      try {
        p.delete(flush: true)
        flash.message = "Successfully deleted"
        redirect(controller: "user", action: "list")

      }
      catch(org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "Permisions ${params.id} could not be deleted ${e.message}"
      }

    }
  }


}
