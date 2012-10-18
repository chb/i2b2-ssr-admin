package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Preference
import edu.chip.i2b2ssr.admin.data.Study

class PreferenceController {
  String menuName = "Edit Preferences"

  def index() {
    redirect(action: editPrefs, params: params)
  }


  def editPrefs = {
    [preference: getPreference(), studies: Study.all]

  }

  def update = {
    flash["message"] = "Preferences saved"
    Preference p = Preference.all.first()
    p.properties = params
    p.shrineCell = trimSlash(p.shrineCell)
    p.i2b2OntCell = trimSlash(p.i2b2OntCell)
    p.save(failOnError: true)
    redirect(action: editPrefs)


  }

  //removes trailing slash at end of URL so we can make
  private String trimSlash(String url){
    if(url.endsWith("/")){
      return url.substring(0, url.size() -2)
    }
    else{
      return url
    }
  }

  Preference getPreference() {
    return Preference.all.first()
  }
}
