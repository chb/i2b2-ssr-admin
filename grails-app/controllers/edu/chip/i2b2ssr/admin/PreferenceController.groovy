package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Preference
import edu.chip.i2b2ssr.admin.data.Study
import org.grails.datastore.mapping.validation.ValidationException

class PreferenceController {
  String menuName = "Edit Preferences"

  def index() {
    redirect(action: editPrefs, params: params)
  }


  def editPrefs = {
    [preference: getPreference(), studies: Study.all]

  }

  def update = {
    try {
      Preference p = Preference.all.first()
      p.shrineCell = trimSlash(p.shrineCell)
      p.i2b2OntCell = trimSlash(p.i2b2OntCell)
      if(params.heartBeatStudy && params.heartBeatStudy != "null") {
        p.heartBeatStudyId
      }
      else {
        p.heartBeatStudy = null
      }
      p.save(failOnError: true)
      flash["message"] = "Preferences saved"

    }
    catch(ValidationException e) {
      flash["message"] = "Validation exception"
    }

    redirect(action: editPrefs)


  }

  //removes trailing slash at end of URL so we can make
  private String trimSlash(String url) {
    if(url.endsWith("/")) {
      return url.substring(0, url.size() - 2)
    }
    else {
      return url
    }
  }

  Preference getPreference() {
    return Preference.all.first()
  }
}
